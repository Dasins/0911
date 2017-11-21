
/**
 * A class to model a object that performs a series of tests 
 * to verify the functionalities of the mail system.
 * 
 * @author David J. Barnes, Michael Kölling, Fran Alvarez, Adrian Bermejo
 * @author Julio Elena, David and Daniel Carmenes
 * @version 2017.11.17
 */
public class Test
{
    // The server used to sending and receiving.
    private MailServer groupD;
    // A user running this server.
    private MailClient franUserAccount;
    // Other user running this server.
    private MailClient julioUserAccount;
    
    /**
     * Create one server with two clients to test the functionalities 
     * of our mail system.
     */
    public Test()
    {
        groupD = new MailServer();
        franUserAccount = new MailClient(groupD, "fran@groupd.dam");
        julioUserAccount = new MailClient(groupD, "julio@groupd.dam");  
    }
    
    /**
     * Check if the number of mails on the server 
     * printed for the user is correct.
     * 
     * @note User must do visual verification.
     */
    public void testFuncionalidad01()
    {
        String to = "julio@groupd.dam";
        String subject = "A simply test message";
        String message = "Blablabla bla blabla";
        // First test. 0 mail on server.
        System.out.println("Checking first functionality...");
        System.out.println("===================");
        System.out.println("Julio checks if he has mail.");
        System.out.println("He expected 0.");
        julioUserAccount.howManyMailItems();
        System.out.println("===================");
        // Second test. 3 mails sends. 3 mails on server.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 3 message to Julio.");
        franUserAccount.sendMailItem(to, subject, message);
        franUserAccount.sendMailItem(to, subject, message);
        franUserAccount.sendMailItem(to, subject, message);
        System.out.println("Julio checks if he has mail.");
        System.out.println("He expected 3.");
        julioUserAccount.howManyMailItems();
        System.out.println("===================");
        // Third test. 3 mails on server, download 2.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Julio downloads 2 mails to read them.");
        julioUserAccount.getNextMailItem();
        julioUserAccount.getNextMailItem();
        System.out.println("Julio checks if he has mail.");
        System.out.println("He expected 1");
        julioUserAccount.howManyMailItems();
        System.out.println("===================");
        //Fourth test. 1 mails on server, download 1.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Julio downloads 1 emails to read them.");
        julioUserAccount.getNextMailItem();
        System.out.println("Julio checks if he has mail.");
        System.out.println("He expected 0");
        julioUserAccount.howManyMailItems();
        System.out.println("===================");
    }
    
    /** 
     * Check if the user can read his last valid mail
     * in all possible cases.
     * 
     * @note User must do visual verification.
     */
    public void testFuncionalidad02()
    {
        String to = "julio@groupd.dam";
        String subject = "A simply test message";
        String message = "Blablabla bla blabla";
        String otherMessage = "The life of the wife is ended by the knife.";
        // First test. No mails.
        System.out.println("===================");
        System.out.println("No mails. Julio tries to read his last mail.");
        julioUserAccount.printLastMailItem();
        System.out.println("===================");
        // Second test, send 1 mail, download and print it. 
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 message to Julio.");
        franUserAccount.sendMailItem(to, subject, message);
        System.out.println("Julio downloads and prints the mail.");
        System.out.println("");
        julioUserAccount.printNextMailItem();
        System.out.println("");
        System.out.println("Julio tries to read his last mail.");
        System.out.println("");
        julioUserAccount.printLastMailItem();
        System.out.println("===================");
        // Third test, send 1 diferent mail, download and read 2 times.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 diferent message to Julio.");
        franUserAccount.sendMailItem(to, subject, otherMessage);
        System.out.println("Julio downloads the new mail");
        julioUserAccount.getNextMailItem();
        System.out.println("Julio tries to read his last mail.");
        System.out.println("");
        julioUserAccount.printLastMailItem();
        System.out.println("");
        System.out.println("Julio tries to read his last mail again.");
        System.out.println("");
        julioUserAccount.printLastMailItem();
        System.out.println("===================");
    }
    
    /** 
     * Check the auto-reply method.
     * 
     * @note User must do visual verification.
     */
    public void testFuncionalidad03()
    {
        String to = "julio@groupd.dam";
        String subject = "A simply test message";
        String message = "Blablabla bla blabla";
        //First test, no mail, try to auto-reply.
        System.out.println("===================");
        System.out.println("No mail" + 
                        "Julio tries to auto-reply his next mail.");
        julioUserAccount.getNextMailAndAutoReply();
        System.out.println("===================");
        // Second test, send 1 mail, auto-reply it and print the reply. 
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 message to Julio.");
        franUserAccount.sendMailItem(to, subject, message);
        System.out.println("Julio tries to auto-reply his next mail.");
        System.out.println("");
        julioUserAccount.getNextMailAndAutoReply();
        System.out.println("Fran downloads and prints the Julio's reply.");
        System.out.println("");
        franUserAccount.printNextMailItem();
        System.out.println("===================");
    }
    
    /**
     * Check the spam filter.
     */
    public void testFuncionalidad04()
    {
        String to = "julio@groupd.dam";
        String subject = "A simply test message";
        String message = "Blablabla bla blabla";
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 message to Julio.");
        franUserAccount.sendMailItem(to, subject, message);
        System.out.println("Julio prints his next mail");
        julioUserAccount.printNextMailItem();
        System.out.println("===================");
        // Second test, Spam message than contains "regalo"
        // with uppercase and lowercase and plural.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 message with spam to Julio.");
        System.out.println("Message: \"TE TRAIGO UNos ReGaLOs\"");
        message = "\"TE TRAIGO UNos ReGaLOs\"";
        franUserAccount.sendMailItem(to, subject, message);
        System.out.println("Julio prints his next mails");
        julioUserAccount.printNextMailItem();
        System.out.println("===================");
        // Second test, Spam message than contains "regalo"
        // with uppercase and lowercase
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 message with spam to Julio.");
        System.out.println("Message: \"ViagrAs barata. Compre ViAgrA\"");
        message = "\"ViagrAs barata. Compre ViAgrA\"";
        franUserAccount.sendMailItem(to, subject, message);
        System.out.println("Julio prints his next mails");
        julioUserAccount.printNextMailItem();
        System.out.println("===================");
    }
}
