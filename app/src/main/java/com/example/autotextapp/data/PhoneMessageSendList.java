package com.example.autotextapp.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class PhoneMessageSendList {
    private Map<String, String[]> messagePhoneList;
    // either have this class hold all my data
    // or have it hold one instance of data and a list in
    // Main activity will hold the rest.

    public PhoneMessageSendList()
    {
        messagePhoneList = new Hashtable<>();
    }

    /**
     * Will sort the phoneNumbers
     * @param message
     * @param phoneNumbers
     */
    public void addMessage_PhoneNumbers(String message, ArrayList<String> phoneNumbers)
    {
        String[] phoneNumbersArray = new String[phoneNumbers.size()];
        phoneNumbers.toArray(phoneNumbersArray);
        addMessage_PhoneNumbers(message, phoneNumbersArray);
    }

    public void addMessage_PhoneNumbers(String message, String[] phoneNumbers)
    {
        Arrays.sort(phoneNumbers);
        messagePhoneList.put(message, phoneNumbers);
    }

    public void removeMessage_PhoneNumbers(String messageToRemove)
    {
        messagePhoneList.remove(messageToRemove);
    }

    /**
     * Gets the message associated with the phoneNumber
     * or null if no message is associated with the phoneNumber.
     * @param phoneNumber the phone number to search for
     * @return the message associated with the phoneNumber, or null.
     */
    public String getMessage(String phoneNumber)
    {
        Set<String> messageList = messagePhoneList.keySet();
        for(String message : messageList)
        {
            String[] phoneNums = messagePhoneList.get(message);
            if(phoneNums != null)
            {
                int index = Arrays.binarySearch(phoneNums, "phoneNumber");
                if(index>=0)
                {
                    return phoneNums[index];
                }
            }
        }
        return null;
    }

    public Set<String> getMessages()
    {
        return messagePhoneList.keySet();
    }

    public String[] getPhoneNumbers(String message)
    {
        return messagePhoneList.get(message);
    }

    public int size()
    {
        return messagePhoneList.size();
    }
}
