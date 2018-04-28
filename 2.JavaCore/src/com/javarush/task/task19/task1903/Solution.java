package com.javarush.task.task19.task1903;

/* 
Адаптация нескольких интерфейсов
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
//        IncomeDataAdapter incomeDataAdapter = new IncomeDataAdapter(new IncomeData() {
//            @Override
//            public String getCountryCode() {
//                return "UA";
//            }
//
//            @Override
//            public String getCompany() {
//                return "JavaRush Ltd.";
//            }
//
//            @Override
//            public String getContactFirstName() {
//                return "Ivan";
//            }
//
//            @Override
//            public String getContactLastName() {
//                return "Ivanov";
//            }
//
//            @Override
//            public int getCountryPhoneCode() {
//                return 38;
//            }
//
//            @Override
//            public int getPhoneNumber() {
//                return 501234567;
//            }
//        });
//        System.out.println(incomeDataAdapter.getPhoneNumber());

    }

    public static class IncomeDataAdapter implements Customer, Contact{
        private IncomeData data;

        public IncomeDataAdapter(IncomeData data) {
            this.data = data;
        }

        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            String countryCode = data.getCountryCode();
            String countryName = countries.get(countryCode);
            return countryName;
        }

        @Override
        public String getName() {
            String firstName = data.getContactFirstName();
            String lastname = data.getContactLastName();
            String name = lastname + ", " + firstName;
            return name;
        }

        @Override
        public String getPhoneNumber() {
            String phoneNumber = String.valueOf(data.getPhoneNumber());
            while (phoneNumber.length()<10) {
                phoneNumber = "0" + phoneNumber;
            }
            String phoneCode = String.valueOf(data.getCountryPhoneCode());
            String phoneNumberResult = "+" + phoneCode + "(" + phoneNumber.substring(0,3) +
                    ")" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6,8) +
                    "-" + phoneNumber.substring(8,10);
            //example +38(050)123-45-67
            return phoneNumberResult;
        }
    }


    public static interface IncomeData {
        String getCountryCode();        //example UA

        String getCompany();            //example JavaRush Ltd.

        String getContactFirstName();   //example Ivan

        String getContactLastName();    //example Ivanov

        int getCountryPhoneCode();      //example 38

        int getPhoneNumber();           //example 501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.

        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan

        String getPhoneNumber();        //example +38(050)123-45-67
    }
}