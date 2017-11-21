
/**
 * A class to model a object that performs a series of tests 
 * to verify the functionalities of the mail system.
 * 
 * @author David J. Barnes, Michael KÃ¶lling, Fran Alvarez, Adrian Bermejo
 * @author Julio Elena, David and Daniel Carmenes
 * @version 2017.11.17
 */
public class Test
{
    // The server used to sending and receiving.
    private MailServer groupD;
    // A user running this server.
    private MailClient franMailClient;
    // Other user running this server.
    private MailClient julioMailClient;
    
    /**
     * Create one server with two clients to test the functionalities 
     * of our mail system.
     */
    public Test()
    {
        groupD = new MailServer();
        franMailClient = new MailClient(groupD, "fran@groupd.dam");
        julioMailClient = new MailClient(groupD, "julio@groupd.dam");  
    }
    
    /**
     * Check if the number of mails on the server 
     * printed for the user is correct.
     * 
     * @note User must do visual verification.
     * @note Watch out! Only if this method is the FIRST method 
     * @note that you invoke print no mail advertisment on terminal.
     */
    public void testFuncionalidad01()
    {
        String to = "julio@groupd.dam";
        String subject = "A simply test message";
        String message = "TestFuncionalidad01";
        // First test. 0 mail on server.
        System.out.println("Checking first functionality...");
        System.out.println("===================");
        System.out.println("Julio checks if he has mail.");
        System.out.println("He expected 0.");
        julioMailClient.howManyMailItems();
        System.out.println("===================");
        // Second test. 3 mails sends. 3 mails on server.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 3 messages to Julio.");
        franMailClient.sendMailItem(to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("Julio checks if he has mail.");
        System.out.println("He expected 3.");
        julioMailClient.howManyMailItems();
        System.out.println("===================");
        // Third test. 3 mails on server, download 2.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Julio downloads 2 mails to read them.");
        julioMailClient.getNextMailItem();
        julioMailClient.getNextMailItem();
        System.out.println("Julio checks if he has mail.");
        System.out.println("He expected 1");
        julioMailClient.howManyMailItems();
        System.out.println("===================");
        //Fourth test. 1 mails on server, download 1.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Julio downloads 1 emails to read them.");
        julioMailClient.getNextMailItem();
        System.out.println("Julio checks if he has mail.");
        System.out.println("He expected 0");
        julioMailClient.howManyMailItems();
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
        // Car
        String to = "julio@groupd.dam";
        String subject = "A simply test message";
        String message = "TestFuncionalidad02";
        String otherMessage = "The life of the wife is ended by the knife.";
        // First test. No mails.
        System.out.println("===================");
        System.out.println("No mails. Julio tries to read his last mail.");
        julioMailClient.printLastMailItem();
        System.out.println("===================");
        // Second test, send 1 mail, download and print it. 
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 message to Julio.");
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("Julio downloads and prints the mail.");
        System.out.println("");
        julioMailClient.printNextMailItem();
        System.out.println("");
        System.out.println("Julio tries to read his last mail.");
        System.out.println("");
        julioMailClient.printLastMailItem();
        System.out.println("===================");
        // Third test, send 1 diferent mail, download and read 2 times.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 diferent message to Julio.");
        franMailClient.sendMailItem(to, subject, otherMessage);
        System.out.println("Julio downloads the new mail");
        julioMailClient.getNextMailItem();
        System.out.println("Julio tries to read his last mail.");
        System.out.println("");
        julioMailClient.printLastMailItem();
        System.out.println("");
        System.out.println("Julio tries to read his last mail again.");
        System.out.println("");
        julioMailClient.printLastMailItem();
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
        System.out.println("No mail. " + 
                        "Julio tries to auto-reply his next mail.");
        julioMailClient.getNextMailAndAutoReply();
        System.out.println("===================");
        // Second test, send 1 mail, auto-reply it and print the reply. 
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 message to Julio.");
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("Julio tries to auto-reply his next mail.");
        System.out.println("");
        julioMailClient.getNextMailAndAutoReply();
        System.out.println("Fran downloads and prints the Julio's reply.");
        System.out.println("");
        franMailClient.printNextMailItem();
        System.out.println("===================");
        //Third test, send 1 mail with spam. Try to auto-reply.
        System.out.println("");
        System.out.println("===================");
        System.out.println("Fran sends 1 message with spam to Julio.");
        message = "Una buena viaga, es un buen regalo";
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("Julio tries to auto-reply his next mail.");
        julioMailClient.getNextMailAndAutoReply();
        System.out.println("===================");
    }
    
    /**
     * Check the spam filter.
     */
    public void testFuncionalidad04()
    {
        int okTests = 0;
        int failTests = 0;
        int numOfTests = 0;
        String from ="fran@grupod.dam";
        String to = "julio@grupod.dam";
        String subject = "Test \"Funcionalidad04()\"";
        boolean itWasExpected = false;
        
        // Test 1, Valid mail.
        numOfTests++;
        String message ="\"¡A los buenos días!\"";
        MailItem item = new MailItem(from, to, subject, message);
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: " + message);
        System.out.printf("It was expected [" + itWasExpected + "]. ");
        System.out.printf("It was obtain [" + julioMailClient.checkSpam(item) + "]. ");
        if (julioMailClient.checkSpam(item) == false){
            System.out.println(" SUCCESS!");
            okTests++;
        }
        else {
            System.out.println(" FAIL!");
            failTests++;
        }
        
        // Test 2, Invalid mail. The message contains "viagra" in lower case.
        numOfTests++;
        message ="\"Do you want a viagra?\"";
        itWasExpected = true;
        item = new MailItem(from, to, subject, message);
        System.out.println("");
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: " + message);
        System.out.printf("It was expected [" + itWasExpected + "]. ");
        System.out.printf("It was obtain [" + julioMailClient.checkSpam(item) + "]. ");
        if (julioMailClient.checkSpam(item) == true){
            System.out.println(" SUCCESS!");
            okTests++;
        }
        else {
            System.out.println(" FAIL!");
            failTests++;
        }
        
        // Test 3, Invalid mail. The message contains "regalo" in lower case.
        numOfTests++;
        message ="\"Do you want a 'regalo'?\"";
        item = new MailItem(from, to, subject, message);
        System.out.println("");
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: " + message);
        System.out.printf("It was expected [" + itWasExpected + "]. ");
        System.out.printf("It was obtain [" + julioMailClient.checkSpam(item) + "]. ");
        if (julioMailClient.checkSpam(item) == true){
            System.out.println(" SUCCESS!");
            okTests++;
        }
        else {
            System.out.println(" FAIL!");
            failTests++;
        }
      
        // Test 4, Invalid mail. The message contains "vIaGrAS" in lower and upper case.
        numOfTests++;
        message ="\"¿Do you want some 'vIaGrAS'?\"";
        item = new MailItem(from, to, subject, message);
        System.out.println("");
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: " + message);
        System.out.printf("It was expected [" + itWasExpected + "]. ");
        System.out.printf("It was obtain [" + julioMailClient.checkSpam(item) + "]. ");
        if (julioMailClient.checkSpam(item) == true){
            System.out.println(" SUCCESS!");
            okTests++;
        }
        else {
            System.out.println(" FAIL!");
            failTests++;
        }
        
        // Test 5, Invalid mail. The message contains "rEGAloS" in lower and upper case.
        numOfTests++;
        message ="\"¿Do you want some 'rEGAloS'?\"";
        item = new MailItem(from, to, subject, message);
        System.out.println("");
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: " + message);
        System.out.printf("It was expected [" + itWasExpected + "]. ");
        System.out.printf("It was obtain [" + julioMailClient.checkSpam(item) + "]. ");
        if (julioMailClient.checkSpam(item) == true){
            System.out.println(" SUCCESS!");
            okTests++;
        }
        else {
            System.out.println(" FAIL!");
            failTests++;
        }
        
        // Results
        System.out.println("");
        System.out.printf("Total of tests: [" + numOfTests + "]. ");
        System.out.printf("Success tests: [" + okTests + "]. ");
        System.out.printf("Fail tests: [" + failTests + "]. ");
    }
}
