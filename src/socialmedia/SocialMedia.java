package socialmedia;

import javax.print.attribute.HashAttributeSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * BadMiniSocialMedia is a minimally compiling, but non-functioning implementor
 * of the MiniSocialMediaPlatform interface.
 *
 * @author Diogo Pacheco
 * @version 1.0
 */

public class SocialMedia implements SocialMediaPlatform {
    // ArrayList to store account details.
    public List<Account> accounts = new ArrayList<>();
    public List<Post> posts = new ArrayList<>(); // remove static
    public int totalPostCount = 0;

    public List<Account> getAccounts() {
        return accounts;
    }

    private static int idCounter = 0;
    private static int post_id = 0;

    public SocialMedia() {

    }

    @Override
    public int createAccount(String handle) throws IllegalHandleException, InvalidHandleException {
        // TODO Auto-generated method
        // Loop through every account that exists in the arraylist and check whether the new handle already exists
        for (Account account : accounts) {
            if (account.getHandle().equals(handle)) {
                throw new IllegalHandleException("Handle already exists!");
            }
        }
        // Check for all other invalid input handles
        if (handle == null || handle.isEmpty() || handle.length() > 30 || handle.contains(" ")) {
            throw new IllegalHandleException("Invalid handle names. A handle should not have any whitespaces, be less than 30 characters in length and not be empty!");
        }
        // Create new account object and return ID of created account, once String handle passes exceptions

        int newId = idCounter;
        idCounter++;
        Account newAccount = new Account(newId, handle);
        accounts.add(newAccount);
        return newId;
    }


    @Override
    public void removeAccount(int newId) throws AccountIDNotRecognisedException {
        // Creates new account object and is set to null
        Account accountToRemove = null;
        // Loop through every account in arrayList and check it matches input id parameter and remove if it matches
        for (Account account : accounts)
            if (account.getId() == newId) {
                accountToRemove = account;
                break;
            }
        if (accountToRemove == null) {
            throw new AccountIDNotRecognisedException("Account with ID" + newId + "Does not exist in the system ");
        }
        accounts.remove(accountToRemove);
    }

    @Override
    public void changeAccountHandle(String oldHandle, String newHandle)
            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
        // Check if handle matches handle present in arraylist
        Account accounttoChange = null;
        for (Account account : accounts) {
            if (account.getHandle() == oldHandle) {
                accounttoChange = account;
                break;
            }
        }
        if (accounttoChange == null) {
            throw new HandleNotRecognisedException("This handle does not exist in the system");
        }

        if (accounts.stream().anyMatch(a -> a.getHandle().equals(newHandle))) {
            throw new IllegalHandleException("There is already an account with this username");
        }

        if (newHandle == null || newHandle.isEmpty() || newHandle.length() > 30 || newHandle.contains(" ")) {
            throw new InvalidHandleException("Invalid handle names. A handle should not have any whitespaces, be less than 30 characters in length and not be empty!");
        }

        accounttoChange.setHandle(newHandle);
    }

    @Override
    public String showAccount(String handle) throws HandleNotRecognisedException {
        Account accounttoshow = null;
        // Loop through accounts arrayList and find matching handle
        for (Account acc : accounts) {
            if (acc.getHandle().equals(handle)) {
                accounttoshow = acc;
                break;
            }
        }
        if (accounttoshow == null) {
            throw new HandleNotRecognisedException("Account with this handle does not exist");
        }
        int totalPostCount = accounttoshow.getPost_count();
        int totalEndorsements = 0;
        for (Post post : accounttoshow.getPosts()) {
            totalEndorsements += accounttoshow.getEndorsements();
        }

        // Strinbuilder object to display all information of account with that handle
        System.out.println("Account summary: ");
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(accounttoshow.getId()).append("\n");
        sb.append("Handle: ").append(accounttoshow.getHandle()).append("\n");
        sb.append("Description: ").append(accounttoshow.getDescription()).append("\n");
        sb.append("Endorse count: ").append(accounttoshow.getEndorsements()).append("\n");
        sb.append("Post count:  ").append(accounttoshow.getPost_count()).append("\n");
        return sb.toString();
    }


    @Override
    public int createPost(String handle, String message) throws HandleNotRecognisedException, InvalidPostException {
        // Loop through Accounts ArrayList and find matching handle
        // Initialise account object originally set to null
        Account account = null;
        for (Account acc : accounts) {
            if (acc.getHandle().equals(handle)) {
                account = acc;
                break;
            }
        }
        if (account == null) {
            throw new HandleNotRecognisedException("This handle was not recognised");
        }
        // Create a post object using string message parameter, then assign unique ID to post and set likes, endorsement ect to default 0
        int postId = totalPostCount + 1;
        Post post = new Post(message,handle,postId,0,0);
        totalPostCount++;


        this.posts.add(post);
//
        account.getPosts().add(post); // one account can have many post
        return postId;
    }



    @Override
    public int endorsePost(String handle, int post_id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        // Find post corresponding to the post id passed in as a parameter.
        // Loop through all posts to find matching one
        Post post_to_be_endorsed = null;
        for (Post p : posts) {
            System.out.println("create post function:" + p.getPost_id());
            if (p.getPost_id() == post_id) {
                post_to_be_endorsed = p;
                break;
            }
        }
        if (post_to_be_endorsed == null){
            throw new PostIDNotRecognisedException("This ID has not been recognised");
        }


        // Find endorsers account String handle
        Account endorsersaccount = null;
        for (Account acc : accounts) {
            if (acc.getHandle().equals(handle)) {
                endorsersaccount = acc;
                acc.increment_account_endorsements();
                break;
            }
        }
        if (endorsersaccount == null) {
            throw new HandleNotRecognisedException("Account with handle" + handle + "does not exist");

        }


        // Create a new post
        int newPostId = totalPostCount + 1;
        totalPostCount++;
        /* <p>
	 * <code>"EP@" + [endorsed account handle] + ": " + [endorsed message]</code>
	 /* <p>*/
        String message = "EP@ " + handle+ ": " + post_to_be_endorsed.getPost_message();
        EndorsementPost endorsementPost = new EndorsementPost(newPostId, handle, post_id, message);

        //Social media and account should store the new post
        this.posts.add(endorsementPost);
        endorsersaccount.getPosts().add(endorsementPost);

        return newPostId;
    }

    @Override
    public int commentPost(String handle, int id, String message) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException, InvalidPostException {
        // Find account with matching handle passed in as a parameter
        Account account = null;
        for(Account acc :accounts){
            if(acc.getHandle().equals(handle)){
                account = acc;
            }
        }
        // Handle exceptions
        if(account.getHandle() == null){
            throw new HandleNotRecognisedException("This handle does not exist!");
        }
        // Loop through all posts and find matching id
        Post post = null;
        for(Post findid: posts){
            if(findid.getPost_id() == id){
                post = findid;
            }
        }
        // Handle post exceptions
        // If post ID does not exist
        if(post == null){
            throw new PostIDNotRecognisedException("This post ID does not exist!");
        }
        // If post message is invalid
        if(post.getPost_message() == " " || post.getPost_message().length() > 100){
            throw new InvalidPostException("This is not a valid post message");
        }
        // Non actionable?
        // Create post object for account that is commenting
        // Assign new post id
        int new_Post_id  = totalPostCount +1;
        totalPostCount++;
        // Create new commentPost object
        CommentPost commentPost = new CommentPost(message,handle,new_Post_id,0,0,id);
        this.posts.add(commentPost);
        return new_Post_id;




    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {

    }

    @Override
    public String showIndividualPost(int id) throws PostIDNotRecognisedException {
        return null;
    }

    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        return null;
    }

    @Override
    public int getMostEndorsedPost() {
        return 0;
    }

    @Override
    public int getMostEndorsedAccount() {
        return 0;
    }

    @Override
    public void erasePlatform() {

    }

    @Override
    public void savePlatform(String filename) throws IOException {

    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {

    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        return 0;
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {

    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {

    }

    @Override
    public int getNumberOfAccounts() {
        return this.accounts.size();
    }

    @Override
    public int getTotalOriginalPosts() {
        return 0;
    }

    @Override
    public int getTotalEndorsmentPosts() {
        return 0;
    }

    @Override
    public int getTotalCommentPosts() {
        return 0;
    }
}
