package co.omise.resources;

import co.omise.models.Customer;
import co.omise.models.ScopedList;
import org.junit.Test;

import java.io.IOException;

public class CustomerResourceTest extends ResourceTest {
    private static final String CUSTOMER_ID = "cust_test_4yq6txdpfadhbaqnwp3";

    @Test
    public void testGetList() throws IOException {
        ScopedList<Customer> list = resource().list();
        assertRequested("GET", "/customers", 200);

        assertEquals(1, list.getTotal());
        assertEquals(20, list.getLimit());

        Customer customer = list.getData().get(0);
        assertEquals("customer", customer.getObject());
        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("John Doe (id: 30)", customer.getDescription());
    }

    @Test
    public void testGet() throws IOException {
        Customer customer = resource().get(CUSTOMER_ID);
        assertRequested("GET", "/customers/" + CUSTOMER_ID, 200);

        assertEquals("customer", customer.getObject());
        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("John Doe (id: 30)", customer.getDescription());
    }

    @Test
    public void testCreate() throws IOException {
        Customer customer = resource().create(new Customer.Create()
                .email("john.doe@example.com")
                .description("John Doe (id: 30)"));
        assertRequested("POST", "/customers", 200);

        assertEquals("customer", customer.getObject());
        assertEquals(CUSTOMER_ID, customer.getId());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("John Doe (id: 30)", customer.getDescription());
    }

    @Test
    public void testUpdate() throws IOException {
        Customer customer = resource().update(CUSTOMER_ID, new Customer.Update()
                .email("john.doe.the.second@example.com"));
        assertRequested("PATCH", "/customers/" + CUSTOMER_ID, 200);

        assertEquals("cust_test_4yq6txdpfadhbaqnwp3", customer.getId());
        assertEquals("john.doe.the.second@example.com", customer.getEmail());
    }

    @Test
    public void testDestroy() throws IOException {
        Customer customer = resource().destroy(CUSTOMER_ID);
        assertRequested("DELETE", "/customers/" + CUSTOMER_ID, 200);

        assertEquals("cust_test_4yq6txdpfadhbaqnwp3", customer.getId());
        assertTrue(customer.isDeleted());
    }

    private CustomerResource resource() {
        return new CustomerResource(testClient());
    }
}