/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes, Michael Kölling, Fran Alvarez, Adrian Bermejo
 * @author Julio Elena, David and Daniel Carmenes
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
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print();
            lastMailItem = item;
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
    
    public void printLastMailItem()
    {
        if (lastMailItem == null){
            System.out.println("You haven't got any message yet.");
        }
        else{
            lastMailItem.print();
        }
    }
}
