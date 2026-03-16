package tests;

import data.Utils.JsonReader;
import data.Utils.StoredDataFromResponse;
import models.UserRequestPayload;
import models.UsersResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.UserApiRequests;

import java.io.IOException;
import java.util.List;

public class UserApiRequestsTests extends TestBase
{
    private UserApiRequests userApi;

    @Test
    public void testCreateUser() throws IOException
    {
        userApi = new UserApiRequests();
        List<UserRequestPayload> userRequestPayloads = JsonReader.loadUserRequests("AddUsersData.json");
        for (UserRequestPayload user : userRequestPayloads)
        {
            UsersResponseBody createdUser = userApi.addNewUser(user.getName(), user.getJob());
            Assert.assertEquals(createdUser.getName(), user.getName(), "Name mismatched");
            Assert.assertEquals(createdUser.getJob(), user.getJob(), "Job mismatched");
            StoredDataFromResponse.createdUserIds.add(createdUser.getId());
        }
    }

    @Test
    public void testUpdateUsers() throws IOException
    {
        userApi = new UserApiRequests();
        List<UserRequestPayload> userRequestPayloads = JsonReader.loadUserRequests("UpdateUserData.json");

        for (int i = 0; i < userRequestPayloads.size(); i++)
        {
            String id = StoredDataFromResponse.createdUserIds.get(i);
            UserRequestPayload user = userRequestPayloads.get(i);

            UsersResponseBody updatedUser = userApi.updateUser(id, user.getName(), user.getJob());

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
        userApi = new UserApiRequests();
        List<UserRequestPayload> userRequestPayloads = JsonReader.loadUserRequests("UpdateUserData.json");
        for (int i = 0; i < userRequestPayloads.size(); i++) {
            String id = StoredDataFromResponse.createdUserIds.get(i);
            UserRequestPayload expectedUser = userRequestPayloads.get(i);
            UsersResponseBody actualUser = userApi.getUser(id);
        }
    }


    @Test
    public void testDeleteUsers()
    {
        userApi = new UserApiRequests();

        for (String id : StoredDataFromResponse.createdUserIds) {
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
        userApi = new UserApiRequests();
        List<UserRequestPayload> userRequestPayloads = JsonReader.loadUserRequests("UpdateUserData.json");
        for (int i = 0; i < userRequestPayloads.size(); i++) {
            String id = StoredDataFromResponse.createdUserIds.get(i);
            UserRequestPayload expectedUser;
            expectedUser = userRequestPayloads.get(i);
            UsersResponseBody actualUser;
            actualUser = userApi.getUser(id);
        }
    }


}


