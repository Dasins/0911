/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes, Michael KÃ¶lling, Fran Alvarez, Adrian Bermejo
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
    // Number of sent mails.
    private int sentMails;
    // Number of received mails.
    private int receivedMails;
    // The mail with longest message;
    private MailItem longerMail;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        lastMailItem = null;
        sentMails = 0;
        receivedMails = 0;
        longerMail = new MailItem("","","","");
    }

    /**
     * @return The next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if (item != null){
            if (checkSpam(item)){
                item = null;
            }
            else{    
                lastMailItem = item;
                checkLengthMMessage(item);
                receivedMails += 1;
            }
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
            System.out.println("Your next mail has been marked as spam.");
            item = null;
        }
        if (item == null){
            System.out.println("No new mail.");
        }
        else if(item.getMessage().substring(0,4).equals("?=? ")){
            String message = item.getMessage();
            message = message.replace("?=? ","");
            message = message.replace("$\\","a");
            message = message.replace("^", "A");
            message = message.replace("*", "e");
            message = message.replace("Ç", "E");
            message = message.replace("º", "i");
            message = message.replace("€", "I");
            message = message.replace("·", "o");
            message = message.replace("!", "O");
            message = message.replace("ª", "u");
            message = message.replace("+", "U");
            String from = item.getFrom();
            String to = item.getTo();
            String subject = item.getSubject();
            item = new MailItem (from,to,subject,message);
            if (checkSpam(item)){
                System.out.println("Your next mail has been marked as spam.");
                item = null;
            }
            else {
                item.print();
            }
        }
        else {
            lastMailItem = item;
            checkLengthMMessage(item);
            receivedMails += 1;
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
        if (!checkSpam(item)){
            sentMails += 1;
        }
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
     public void autoReplyNextMailItem()
    { 
       MailItem item = server.getNextMailItem(user);
       if(item == null){
           System.out.println("Nothing to reply.");
       }
       else if (checkSpam(item))
       {
           System.out.println("Your next mail has been marked as spam.");
       }
       else{
           String subject = "Re: " + item.getSubject();
           String message = "Thanks for your mail.\n" 
                                + item.getMessage();
           checkLengthMMessage(item);
           lastMailItem = item;
           sendMailItem(item.getFrom(), subject, message);
           receivedMails += 1;
       }
    }
    
    /**
     * Returns a value of true if an invalid string appears in the message.
     * If not returns false
     * @param item The mail item that is checked for spam.
     * @return True if message is spam. False if not.
     */
    public boolean checkSpam(MailItem item)
    {
        boolean mailIsSpam = false;
        if (item.getMessage().toLowerCase().indexOf("viagra") > 0){
            mailIsSpam = true;
        }
        if (item.getMessage().toLowerCase().indexOf("regalo") > 0){
            mailIsSpam = true;
        }
        return mailIsSpam;
    }
    
    /**
     * Check the length of a received mail message.
     * If the length of the received mail is greeter than the last longer message, remplace it.
     * @note If a received mail message has the same length of th longer message, this method don't store it.
     */
    private void checkLengthMMessage(MailItem item)
    {
        if(item.getMessage().length() > longerMail.getMessage().length()){
            longerMail = item;
        }
    }
    
    /**
     * Print on text terminal the number of sent/received messages 
     * and the longest message you received sender.
     */
    public void printMailStatistics()
    {
        if(receivedMails > 0 )
        {
           System.out.println("Sent: [" + sentMails +  "], Received: [" + receivedMails 
                                + "], Longer mail from: [" + longerMail.getFrom() 
                                + "]. with " + longerMail.getMessage().length() + " characters");
        }
        else
        {
           System.out.println("Sent: [" + receivedMails + "], Received: [" + sentMails + "].");
        }
    }
    
    /**
     * Encrypt and send the given message  to the given recipient via
     * the attached mail server. Encrypted mail message starts with ?=?.
     * @param to The intended recipient.
     * @param subject The subject of the message.
     * @param message The original text of the message to be sent.
     */
    public void sendEncryptedMailItem(String to, String subject, String message)
    {
        MailItem item = new MailItem(user,to,subject,message);
        if (!checkSpam(item)){
            sentMails += 1;
        }
        message = message.replace("a","$\\");
        message = message.replace("A", "^");
        message = message.replace("e", "*");
        message = message.replace("E", "Ç");
        message = message.replace("i", "º");
        message = message.replace("I", "€");
        message = message.replace("o", "·");
        message = message.replace("O", "!");
        message = message.replace("u", "ª");
        message = message.replace("U", "+");
        message = "?=? " + message;
        item = new MailItem(user,to,subject,message);
        server.post(item);
    }
}