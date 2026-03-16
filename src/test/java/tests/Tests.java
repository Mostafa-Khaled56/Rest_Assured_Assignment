package tests;

import data.Utils.DataReader;
import data.Utils.StoredResponse;
import models.Payload;
import models.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.UserRequests;

import java.io.IOException;
import java.util.List;

public class Tests extends TestBase
{
    private UserRequests userApi;

    @Test
    public void testCreateUser() throws IOException
    {
        userApi = new UserRequests();
        List<Payload> payloads = DataReader.loadUserRequests("AddUsersData.json");
        for (Payload user : payloads)
        {
            Response createdUser = userApi.addNewUser(user.getName(), user.getJob());
            Assert.assertEquals(createdUser.getName(), user.getName(), "Name mismatched");
            Assert.assertEquals(createdUser.getJob(), user.getJob(), "Job mismatched");
            StoredResponse.createdUserIds.add(createdUser.getId());
        }
    }

    @Test
    public void testUpdateUsers() throws IOException
    {
        userApi = new UserRequests();
        List<Payload> payloads = DataReader.loadUserRequests("UpdateUserData.json");

        for (int i = 0; i < payloads.size(); i++)
        {
            String id = StoredResponse.createdUserIds.get(i);
            Payload user = payloads.get(i);

            Response updatedUser = userApi.updateUser(id, user.getName(), user.getJob());

            // Assertions
            Assert.assertEquals(updatedUser.getName(), user.getName(), "Name mismatch");
            Assert.assertEquals(updatedUser.getJob(), user.getJob(), "Job mismatch");
        }
    }

    /*
    This method to Verify the updated users, the called endpoint in this method always retrieves empty body "empty array"
    so it can't be useful to verify the updated users.
    Instead of this method, i added assertion in the previous method testUpdateUsers() to verify the updated users.
     */
    @Test
    public void testVerifyUpdatedUsers() throws IOException {
        userApi = new UserRequests();
        List<Payload> payloads = DataReader.loadUserRequests("UpdateUserData.json");
        for (int i = 0; i < payloads.size(); i++) {
            String id = StoredResponse.createdUserIds.get(i);
            Payload expectedUser = payloads.get(i);
            Response actualUser = userApi.getUser(id);
        }
    }


    @Test
    public void testDeleteUsers()
    {
        userApi = new UserRequests();

        for (String id : StoredResponse.createdUserIds) {
            boolean isDeleted = userApi.deleteUser(id);
            Assert.assertTrue(isDeleted, "Failed to delete user with ID: " + id);
        }
    }

    /*
    This method to Verify the deleted users, the called endpoint in this method always retrieves empty body "empty array"
    so it can't be useful to verify the updated users.
    Instead of this method, i added assertion "by status code" inside the called method userApi.deleteUser(id) in the previous method testDeleteUsers() to verify the updated users.
     */
    @Test
    public void testVerifyDeletedUsers() throws IOException {
        userApi = new UserRequests();
        List<Payload> payloads = DataReader.loadUserRequests("UpdateUserData.json");
        for (int i = 0; i < payloads.size(); i++) {
            String id = StoredResponse.createdUserIds.get(i);
            Payload expectedUser;
            expectedUser = payloads.get(i);
            Response actualUser;
            actualUser = userApi.getUser(id);
        }
    }


}


