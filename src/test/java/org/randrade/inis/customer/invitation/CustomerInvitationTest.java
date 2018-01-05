package org.randrade.inis.customer.invitation;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.randrade.inis.customer.invitation.model.Customer;

import com.google.gson.JsonSyntaxException;

public class CustomerInvitationTest {

    @Test
    public void testReadCustomerFile() {

        CustomerInvitation customerInvitation = new CustomerInvitation();
        try {
            List<Customer> customerList = customerInvitation.printCustomerInvitationList("file.txt");
            Assert.assertEquals(customerList.size(), 2);

        } catch (FileNotFoundException e) {
        	Assert.fail();
        }
    }

    @Test
    public void testCustomerSortedById() {

        CustomerInvitation customerInvitation = new CustomerInvitation();
        try {
            List<Customer> customerList = customerInvitation.printCustomerInvitationList("file.txt");
            Assert.assertEquals(customerList.get(0).getId().intValue(), 4);

        } catch (FileNotFoundException e) {
        	Assert.fail();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void testReadCustomerFileNotFound() throws FileNotFoundException {

        CustomerInvitation customerInvitation = new CustomerInvitation();
        customerInvitation.printCustomerInvitationList("file1.txt");
    }

    @Test(expected = JsonSyntaxException.class)
    public void testReadCustomerFileParseError() throws FileNotFoundException {

        CustomerInvitation customerInvitation = new CustomerInvitation();
        customerInvitation.printCustomerInvitationList("file-error.txt");
    }

    @Test
    public void testReadCustomerFileEmpty() throws FileNotFoundException {

        CustomerInvitation customerInvitation = new CustomerInvitation();
        List<Customer> customerList = customerInvitation.printCustomerInvitationList("file-empty.txt");

        Assert.assertNull(customerList);
    }
}
