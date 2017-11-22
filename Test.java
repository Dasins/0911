
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
    
    /**
     * Create one server with two clients to test the functionalities 
     * of our mail system.
     */
    public Test()
    { 
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
        MailServer groupD = new MailServer();
        MailClient franMailClient = new MailClient(groupD, "fran@groupd.dam");
        MailClient julioMailClient = new MailClient(groupD, "julio@groupd.dam");
        String to = "julio@groupd.dam";
        String subject = "Test \"Funcionalidad01()\"";
        String message = "";
        int numOfTests = 0;
        System.out.println("Testing functionality01...");
        System.out.println("");
        //Test 1: No mails on server
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("0 mails on server. ");
        System.out.println("Expected: [0].");
        julioMailClient.howManyMailItems();
        //Test 2: Two mails on server.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Other mail client sent 1 mail to ours mail client.");
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("Expected: [1].");
        julioMailClient.howManyMailItems();
        System.out.println("");
        //Test 3: Download and auto-reply a mail.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Download and auto-reply a mail ");
        julioMailClient.autoReplyNextMailItem();
        System.out.println("Expected: [0].");
        julioMailClient.howManyMailItems();
        System.out.println("");
        //Test 4: Received 6 spam mails on server.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Other mail client sent 6 spam mails to ours mail client.");
        message = "Take a \"viagra\".";
        franMailClient.sendMailItem(to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("Expected: [6].");
        julioMailClient.howManyMailItems();
        System.out.println("");
        //Test 5: Download and auto-reply the spam mail..
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Download and auto-reply the spam mail.");
        julioMailClient.autoReplyNextMailItem();
        System.out.println("Expected: [5].");
        julioMailClient.howManyMailItems();
        System.out.println("");
        //Test 6: Other mail client sent 1 encripted mail and other mail to ours.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("1 mail and 1 encrypted mail sent to our mail client");
        System.out.println("Expected: [7].");
        julioMailClient.howManyMailItems();
        System.out.println("");
    }
    
    /** 
     * Check if the user can read his last valid mail
     * in all possible cases.
     * 
     * @note User must do visual verification.
     */
    public void testFuncionalidad02()
    {
        MailServer groupD = new MailServer();
        MailClient franMailClient = new MailClient(groupD, "fran@groupd.dam");
        MailClient julioMailClient = new MailClient(groupD, "julio@groupd.dam");
        String from = "fran@groupd.dam";
        String to = "julio@groupd.dam";
        String subject = "Test \"Funcionalidad02()\"";
        String message = "";
        int numOfTests = 0;
        MailItem expectedItem = new MailItem (from, to, subject, message);
        System.out.println("Testing functionality02...");
        System.out.println("");
        // Test 1: No mails received
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("No mails received.");
        julioMailClient.printLastMailItem();
        System.out.println("");
        // Test 2: 1 mail received and download
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Mail sent to ours and download.");
        message = "Hello!";
        expectedItem = new MailItem (from, to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("");
        System.out.println("Expected:");
        System.out.println("--------------------");
        expectedItem.print();
        System.out.println("--------------------");
        System.out.println("");
        System.out.println("Obtained:");
        julioMailClient.getNextMailItem();
        julioMailClient.printLastMailItem();
        System.out.println("--------------------");
        System.out.println("");
        // Test 3: 3 Mail received, print 3.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("3 different mails sent to ours and printed:");
        message = "First message";
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        message = "Second message";
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("--------------------");;
        julioMailClient.printNextMailItem();
        message = "Third message";
        expectedItem = new MailItem (from, to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        System.out.println("--------------------");
        System.out.println("");
        System.out.println("Expected:");
        System.out.println("--------------------");
        expectedItem.print();
        System.out.println("--------------------");
        System.out.println("");
        System.out.println("Obtained:");
        julioMailClient.printLastMailItem();
        System.out.println("--------------------");
        System.out.println("");
        // Test 4: Mail received and auto-reply.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Mail sent to ours and auto-reply.");
        message = "Can you see me?";
        expectedItem = new MailItem (from, to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("");
        System.out.println("Expected:");
        System.out.println("--------------------");
        expectedItem.print();
        System.out.println("--------------------");
        System.out.println("");
        System.out.println("Obtained:");
        julioMailClient.autoReplyNextMailItem();
        julioMailClient.printLastMailItem();
        System.out.println("--------------------");
        System.out.println("");
        // Test 5: Spam mail received and download.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Spam mail sent to ours and download.");
        message = "Take a \"viagra\".";
        franMailClient.sendMailItem(to, subject, message);
        julioMailClient.getNextMailItem();
        System.out.println("--------------------");
        julioMailClient.printLastMailItem();
        System.out.println("--------------------");
        System.out.println("");
        // Test 6: Spam mail received and printed.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Spam mail sent to ours and printed:");
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        System.out.println("");
        System.out.println("Expected:");
        System.out.println("--------------------");
        expectedItem.print();
        System.out.println("--------------------");
        System.out.println("");
        System.out.println("Obtained:");
        julioMailClient.printLastMailItem();
        System.out.println("--------------------");
        System.out.println("");
        // Test 7: Spam mail received and auto-reply.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Spam mail sent to ours and auto-reply.");
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("");
        System.out.println("Expected:");
        System.out.println("--------------------");
        expectedItem.print();
        System.out.println("--------------------");
        System.out.println("");
        System.out.println("Obtained:");
        julioMailClient.printLastMailItem();
        System.out.println("--------------------");
        System.out.println("");
    }
    
    /** 
     * Check the auto-reply method.
     * 
     * @note User must do visual verification.
     */
    public void testFuncionalidad03()
    {
        MailServer groupD = new MailServer();
        MailClient franMailClient = new MailClient(groupD, "fran@groupd.dam");
        MailClient julioMailClient = new MailClient(groupD, "julio@groupd.dam");
        String from = "fran@groupd.dam";
        String to = "julio@groupd.dam";
        String subject = "Test \"Funcionalidad03()\"";
        String message = "";
        int numOfTests = 0;
        MailItem expectedItem = new MailItem (from, to, subject, message);
        System.out.println("Testing functionality03...");
        System.out.println("");
        // Test 1: No mails received
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("No mails received.");
        julioMailClient.autoReplyNextMailItem();
        System.out.println("");
        // Test 2: 1 mail received and autoreply
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Mail sent to ours and autoreply.");
        message = "Hello!";
        expectedItem = new MailItem (from, to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        julioMailClient.autoReplyNextMailItem();
        System.out.println("Our mail received:");
        System.out.println("--------------------");
        julioMailClient.printLastMailItem();
        System.out.println("--------------------");
        System.out.println("Their mail received");
        franMailClient.printNextMailItem();
        System.out.println("");
        // Test 3: 1 Spam mail received.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Spam mail sent to ours and autoreply:");
        message = "Take a viagra";
        franMailClient.sendMailItem(to, subject, message);
        expectedItem = new MailItem (from, to, subject, message);
        julioMailClient.autoReplyNextMailItem();
        System.out.println("--------------------");
        System.out.println("Their mail received");
        franMailClient.printNextMailItem();
        System.out.println("--------------------");
        System.out.println("");
    }
    
    /**
     * Check the spam filter.
     */
    public void testFuncionalidad04()
    {
        MailServer groupD = new MailServer();
        MailClient franMailClient = new MailClient(groupD, "fran@groupd.dam");
        MailClient julioMailClient = new MailClient(groupD, "julio@groupd.dam"); 
        int okTests = 0;
        int failTests = 0;
        int numOfTests = 0;
        String from ="fran@grupod.dam";
        String to = "julio@grupod.dam";
        String subject = "Test \"Funcionalidad04()\"";
        boolean itWasExpected = false;
        System.out.println("Testing functionality04...");
        System.out.println("");
        // Test 1, Valid mail.
        numOfTests++;
        String message ="Have a good day!";
        MailItem item = new MailItem (from, to, subject, message);
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: \"" + message + "\"");
        System.out.printf("It was expected [" + itWasExpected + "], ");
        System.out.printf("it was obtain [" + julioMailClient.checkSpam(item) + "]. ");
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
        message ="Do you want a viagra?";
        itWasExpected = true;
        item = new MailItem (from, to, subject, message);
        System.out.println("");
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: \"" + message + "\"");
        System.out.printf("It was expected [" + itWasExpected + "], ");
        System.out.printf("it was obtain [" + julioMailClient.checkSpam(item) + "]. ");
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
        message ="Do you want a 'regalo'?";
        item = new MailItem (from, to, subject, message);
        System.out.println("");
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: \"" + message + "\"");
        System.out.printf("It was expected [" + itWasExpected + "], ");
        System.out.printf("it was obtain [" + julioMailClient.checkSpam(item) + "]. ");
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
        item = new MailItem (from, to, subject, message);
        System.out.println("");
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: " + message);
        System.out.printf("It was expected [" + itWasExpected + "], ");
        System.out.printf("it was obtain [" + julioMailClient.checkSpam(item) + "]. ");
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
        item = new MailItem (from, to, subject, message);
        System.out.println("");
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("The message of mail is: " + message);
        System.out.printf("It was expected [" + itWasExpected + "], ");
        System.out.printf("it was obtain [" + julioMailClient.checkSpam(item) + "]. ");
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
        System.out.println("Total of tests: [" + numOfTests + "]. ");
        System.out.println("Success tests: [" + okTests + "]. ");
        System.out.println("Fail tests: [" + failTests + "]. ");
    }
    
    /**
     * Check the mail statistics system.
     */
    public void testFuncionalidad05()
    {
        MailServer groupD = new MailServer();
        MailClient franMailClient = new MailClient(groupD, "fran@groupd.dam");
        MailClient julioMailClient = new MailClient(groupD, "julio@groupd.dam");
        String from = "fran@groupd.dam";
        String to = "julio@groupd.dam";
        String subject = "Test \"Funcionalidad05()\"";
        String message = "";
        int numOfTests = 0;
        System.out.println("Testing functionality05...");
        System.out.println("");
        // Test 1: No mails received
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("No mails received.");
        System.out.println("Expected: Sent: [0], Received: [0], ");
        julioMailClient.printMailStatistics();
        System.out.println("");
        // Test 2: 1 mail received and download.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Mail sent to ours and download");
        message = "Hello!";
        franMailClient.sendMailItem(to, subject, message);
        julioMailClient.getNextMailItem();
        System.out.println("Expected: Sent: [0], Received: [1], ");
        System.out.println("Longer mail from: [fran@groupd.dam]");
        julioMailClient.printMailStatistics();
        System.out.println("");
        // Test 3: 2 mails sent.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("We sent two mails");
        to = "fran@groupd.dam";
        julioMailClient.sendMailItem(to, subject, message);
        julioMailClient.sendMailItem(to, subject, message);
        System.out.println("Expected: Sent: [2], Received: [1], ");
        System.out.println("Longer mail from: [fran@groupd.dam]");
        julioMailClient.printMailStatistics();
        System.out.println("");
        // Test 4: Longer mail from changes
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("4 mails with different length sent to ours and print");
        to = "julio@groupd.dam";
        System.out.println("Mails:");
        message = "I'm not the longer mail.";
        franMailClient = new MailClient(groupD, "adrian@groupd.dam");
        franMailClient.sendMailItem(to, subject, message);
        message = "I'm not the longer mail, sorry.";
        franMailClient = new MailClient(groupD, "daniel@groupd.dam");
        franMailClient.sendMailItem(to, subject, message);
        message = "Of course, I'm the mail with the greater length.\nI'm the longeeeeeeest!";
        franMailClient = new MailClient(groupD, "david@groupd.dam");
        franMailClient.sendMailItem(to, subject, message);
        message = "Wrong!";
        franMailClient = new MailClient(groupD, "fran@groupd.dam");
        franMailClient.sendMailItem(to, subject, message);
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        System.out.println("--------------------");
        System.out.println("Expected: Sent: [2], Received: [5], ");
        System.out.println("Longer mail from: [david@groupd.dam]");
        julioMailClient.printMailStatistics();
        System.out.println("");
        // Test 5: One mail received and auto-reply
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Mail sent to our mail server and auto-reply");
        franMailClient.sendMailItem(to, subject, message);
        julioMailClient.autoReplyNextMailItem();
        System.out.println("Expected: Sent: [3], Received: [6], ");
        System.out.println("Longer mail from: [david@groupd.dam]");
        julioMailClient.printMailStatistics();
        System.out.println("");
        // Test 6: Spam mail sent.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("We sent spam mail");
        message = "Do you need some viagra?";
        julioMailClient.sendMailItem(to, subject, message);
        System.out.println("Expected: Sent: [3], Received: [6], ");
        System.out.println("Longer mail from: [david@groupd.dam]");
        julioMailClient.printMailStatistics();
        System.out.println("");
        // Test 7: 3 Spam mail received and download, print and autoreply.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("3 spam mails sent to our mail server, one download, \nother print and last auto-reply");
        System.out.println("Note: One have more length than the actually longer mail.");
        franMailClient.sendMailItem(to, subject, message);
        franMailClient.sendMailItem(to, subject, message);
        message = "One viagra, Two viagra, Three viagra, Four Viagra, Five Viagra, Six Viagra, Seven Viagra, Eight viagra, Nine viagra";
        franMailClient.sendMailItem(to, subject, message);
        julioMailClient.getNextMailItem();
        julioMailClient.printNextMailItem();
        julioMailClient.autoReplyNextMailItem();
        System.out.println("Expected: Sent: [3], Received: [6], ");
        System.out.println("Longer mail from: [david@groupd.dam]");
        julioMailClient.printMailStatistics();
        System.out.println("");
        // Test 8: 3 Encrypted mail sent.
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Encrypted mail sent");
        message = "The meaning of life is 42."; 
        franMailClient.sendEncryptedMailItem(to, subject, message);
        System.out.println("Expected: Sent: [4], Received: [6], ");
        System.out.println("Longer mail from: [david@groupd.dam]");
        julioMailClient.printMailStatistics();
        System.out.println("");
    }
    
    
    /**
     * Check encrypt method.
     */
    public void testFuncionalidad06()
    {
        MailServer groupD = new MailServer();
        MailClient franMailClient = new MailClient(groupD, "fran@groupd.dam");
        MailClient julioMailClient = new MailClient(groupD, "julio@groupd.dam");
        String from = "fran@groupd.dam";
        String to = "julio@groupd.dam";
        String subject = "Test \"Funcionalidad06()\"";
        String message = "This mail contains classified information.\nThe meaning of life is 42.";
        int numOfTests = 0;
        System.out.println("Testing functionality06...");
        System.out.println("");
        // Test 1: Normal mail
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Mail sent to our mail server and printed.");
        franMailClient.sendMailItem (to, subject, message);
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        System.out.println("--------------------");
        System.out.println("");
        // Test 2: Encrypted mail
        numOfTests++;
        System.out.println("##- TEST " + numOfTests + " -##");
        System.out.println("Encrypted mail sent to our mail server and printed.");
        franMailClient.sendEncryptedMailItem (to, subject, message);
        System.out.println("--------------------");
        julioMailClient.printNextMailItem();
        System.out.println("--------------------");
        System.out.println("");
    }
}
