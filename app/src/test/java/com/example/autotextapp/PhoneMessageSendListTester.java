package com.example.autotextapp;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.autotextapp.data.PhoneMessageSendList;

import java.util.ArrayList;

/**
 * Tests for the class to make sure it works.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PhoneMessageSendListTester {
    @Test
    public void createClass_noParams()
    {
        PhoneMessageSendList list = new PhoneMessageSendList();
        assertEquals(0, list.size());
    }

    @Test
    public void addRetrieveArrayTest()
    {
        PhoneMessageSendList list = new PhoneMessageSendList();
        String message = "first";
        String[] expected_phoneNumbers = {"+18092120000", "+11233215323", "+14056333467"};

        list.addMessage_PhoneNumbers(message, expected_phoneNumbers);
        String[] actual_phoneNumbers = list.getPhoneNumbers(message);

        assertArrayEquals(expected_phoneNumbers, actual_phoneNumbers);
    }

    @Test
    public void addRetrieveArrayListTest()
    {
        PhoneMessageSendList list = new PhoneMessageSendList();
        String message = "first";
        String[] expected_phoneNumbers = {"+18092120000", "+11233215323", "+14056333467"};
        ArrayList<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("+18092120000");
        phoneNumbers.add("+11233215323");
        phoneNumbers.add("+14056333467");

        list.addMessage_PhoneNumbers(message, phoneNumbers);
        String[] actual_phoneNumbers = list.getPhoneNumbers(message);

        assertArrayEquals(expected_phoneNumbers, actual_phoneNumbers);
    }

    @Test
    public void testGetMessage()
    {
        PhoneMessageSendList list = new PhoneMessageSendList();
        String[] messageList = {"first","second","third","fourth","fifth","sixth","seventh","ninth","tenth"};
        String[][] phoneNumbers_all = phoneNumLists(messageList.length,10);
        for(int i = 0; i<messageList.length; i++)
        {
            list.addMessage_PhoneNumbers(messageList[i], phoneNumbers_all[i]);
        }
        String expected_message = messageList[4];
        String phoneNum = phoneNumbers_all[4][5];

        String actual_message = list.getMessage(phoneNum);

        assertEquals(expected_message, actual_message);
    }

    @Test
    public void testGetMessage2()
    {
        PhoneMessageSendList list = new PhoneMessageSendList();
        String[] messageList = {"first","second","third","fourth","fifth","sixth","seventh","eighth","ninth","tenth"};
        String[][] phoneNumbers_all = phoneNumLists(messageList.length,10);
        for(int i = 0; i<messageList.length; i++) {
            list.addMessage_PhoneNumbers(messageList[i], phoneNumbers_all[i]);
        }
        String expected_message = messageList[9];
        String phoneNum = phoneNumbers_all[9][9];

        String actual_message = list.getMessage(phoneNum);

        assertEquals(expected_message, actual_message);
    }

    public String[][] phoneNumLists(int numberOfLists, int sizeOfLists)
    {
        String[][] phoneNumbersArray = new String[numberOfLists][sizeOfLists];
        int numberInserted = 1000000000;
        for(int i = 0; i< numberOfLists; i++)
        {
            for(int j = 0; j<sizeOfLists; j++)
            {
                phoneNumbersArray[i][j] = "+1"+numberInserted;
                numberInserted++;
            }
        }
        return phoneNumbersArray;
    }
}
