package com.testTask.contacts.dao;

public class ContactFactory {

        public static ExecutionContact getContactDao(){
            return new ExecutionContactSimple();
        }

}
