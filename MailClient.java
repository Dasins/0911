/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes, Michael Kölling, Fran Alvarez, Adrian Bermejo
 * @author Julio Elena, David Rodriguez and Daniel Carmenes
 * @version 2017.11.17
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    // The last valid mail item.
    private MailItem lastMailItem;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        lastMailItem = null;
    }

    /**
     * @return The next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if (checkSpam(item) && item != null){
            item = null;
        }
        if (item != null){
            lastMailItem = item;
        }
        return item;
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if (checkSpam(item)){
            System.out.println("Your next email has been marked as spam.");
        }
        else if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print();
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param subject The subject of the message.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String subject, String message)
    {
        MailItem item = new MailItem(user, to, subject, message);
        server.post(item);
    }
    
    /**
     * Print on the text terminal 
     * the number of mail items for the user on the mail server.
     */
    public void howManyMailItems()
    {
       System.out.println("You have " + server.howManyMailItems(user) +
                           " messages to read.");  
    }
    
    /**
     * Print to the text terminal the last mail item received if any, 
     * or print an advertisement if not.
     */
    public void printLastMailItem()
    {
        if (lastMailItem == null){
            System.out.println("You haven't got any message yet.");
        }
        else{
            lastMailItem.print();
        }
    }
    
    /**
     * Download the next mail item (if any) for this user
     * When it is valid send an auto-reply mail to the sender. 
     */
     public void getNextMailAndAutoReply()
    { 
       // Change line under this comments 
       // if you want to auto-reply spam mails.
       // MailItem item = server.getNextMailItem(user);
       MailItem item = server.getNextMailItem(user);
       if(item == null){
           System.out.println("Nothing to reply.");
       }
       else{
           String subject = "Re: " + item.getSubject();
           String message = "Thanks for your mail.\n" 
                                + item.getMessage();
           sendMailItem(item.getFrom(), subject, message);
       }
    }
    
    /**
     * Returns a value of true if an invalid string appears in the message.
     * If not returns false
     * 
     * @param item The mail item that is checked for spam.
     * @return True if message is spam. False if not.
     */
    private boolean checkSpam(MailItem item)
    {
        boolean spam = false;
        if (item.getMessage().toLowerCase().indexOf("viagra") > 0){
            spam = true;
        }
        if (item.getMessage().toLowerCase().indexOf("regalo") > 0){
            spam = true;
        }
        return spam;
    }
}