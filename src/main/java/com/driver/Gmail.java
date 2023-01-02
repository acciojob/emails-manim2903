package com.driver;

import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;
import java.util.Hashtable;

public class Gmail extends Email {

    private int inboxSize;
    private int trashSize;

    int inboxCapacity;
    //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    ArrayList<EmailTemplate> inbox;
    ArrayList<EmailTemplate> trash;
//    private ArrayList<Triple<Date,String,String>> inbox;
//    private ArrayList<Triple<Date,String,String>> trash;
    public Gmail(String emailId, int inboxCapacity) {
       super(emailId);
       this.inboxCapacity=inboxCapacity;
       this.inbox=new ArrayList<>();
       this.trash=new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
//
//        if(inbox.size()==inboxCapacity) {
//            Triple<Date, String, String> oldestMail = inbox.get(0);
//            inbox.remove(0);
//            trash.add(oldestMail);
//        }
//        Triple<Date,String,String> mail=Triple.of(date,sender,message);
//        inbox.add(mail);

        if(inbox.size()==inboxCapacity){// Its full
            //get the first email template
            EmailTemplate emailTemplate=inbox.get(0);

            //removing from inbox
            inbox.remove(0);

            //adding to trash
            trash.add(emailTemplate);
        }
        //add the latest email
            EmailTemplate emailTemplate=new EmailTemplate(date,sender,message);
            inbox.add(emailTemplate);

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        //we have array list of emailTemplate
        //find its index : matching the message with emialTemplate message
        EmailTemplate emailTemplate=null;
        for(int i=0;i<inbox.size();i++){
            EmailTemplate emailTemplate1=inbox.get(i);
            if (emailTemplate1.message.equals(message)){
                emailTemplate=emailTemplate1;
                break;
            }
        }
        if(emailTemplate != null){
            inbox.remove(emailTemplate);
            trash.add(emailTemplate);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if (inbox.isEmpty())
            return null;
        EmailTemplate emailTemplate=inbox.get(inbox.size()-1);
        return emailTemplate.message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        int n=inbox.size();
        if(inbox.size()==0){
            return null;
        }
        //oldest message : get the first message
        EmailTemplate emailTemplate=inbox.get(0);
        return emailTemplate.message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

        int count=0;
        //iterate over the array list
        for(int i=0;i<inbox.size();i++){
            EmailTemplate emailTemplate=inbox.get(i);

            //compare the date;
            if((emailTemplate.date.compareTo(start)>=0) && (emailTemplate.date.compareTo(end)<=0)){
                count++;
            }
        }
        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inboxSize=inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trashSize;
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
