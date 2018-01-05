package org.randrade.inis.customer.invitation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.randrade.inis.customer.invitation.model.Address;
import org.randrade.inis.customer.invitation.model.Customer;


import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;


public class CustomerInvitation {

    private static String DO_LATITUDE = "53.339428";
    private static String DO_LONGITUDE = "-6.257664";
    private static BigDecimal MAX = new BigDecimal("314");

    public static void main(String[] args) throws FileNotFoundException {

        CustomerInvitation customerInvitation = new CustomerInvitation();
        customerInvitation.printCustomerInvitationList("gistfile1.txt");
    }

    public List<Customer> printCustomerInvitationList(String filename) throws FileNotFoundException {

        List<Customer> rawCustomerList = readCustomerListFromJSONFile(filename);

        if (rawCustomerList.size() > 0) {

            List<Customer> validCustomerList = findValidCustomersFromList(rawCustomerList);

            sortById(validCustomerList);           

            Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            System.out.println(g.toJson(validCustomerList));

            return validCustomerList;
        }

        return null;
    }

    private List<Customer> findValidCustomersFromList(List<Customer> rawCustomerList) {

    	Address fromDublinOffice = new Address(DO_LATITUDE, DO_LONGITUDE);
    	
        List<Customer> validCustomerList = new ArrayList<Customer>();
        
        for (Customer c : rawCustomerList) {
        	
        	BigDecimal distance = c.calculate(fromDublinOffice);
            
            if (distance.compareTo(MAX) <= 0) {
            	validCustomerList.add(c);
            }
        }

        return validCustomerList;
    }
    		
    private void sortById(List<Customer> validCustomerList) {

        Collections.sort(validCustomerList, new Comparator<Customer>() {
            public int compare(Customer o1, Customer o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
    }   
  

    private List<Customer> readCustomerListFromJSONFile(String filename) throws FileNotFoundException {

        List<Customer> customerList = new ArrayList<Customer>();

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(filename);

        if (url != null) {

            File file = new File(url.getFile());

            Scanner scanner = new Scanner(file);

            Gson gson = new Gson();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Customer c = gson.fromJson(line, Customer.class);
                customerList.add(c);
            }

            scanner.close();

        } else {
            throw new FileNotFoundException();
        }

        return customerList;


    }

}

