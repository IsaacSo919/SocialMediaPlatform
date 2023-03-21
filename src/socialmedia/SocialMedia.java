package socialmedia;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * BadMiniSocialMedia is a minimally compiling, but non-functioning implementor
 * of the MiniSocialMediaPlatform interface.
 *+
 * @author Diogo Pacheco
 * @version 1.0
 */

public class SocialMedia implements SocialMediaPlatform {
    // ArrayList to store account details.
    public List<Account> accounts = new ArrayList<>();
    public List<Post> posts_list = new ArrayList<>(); // remove static

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
        for (Integer post : accounttoshow.getListOfPostIDsInThisAccount()) {
            totalEndorsements += accounttoshow.getEndorsements();
        }

        // Strinbuilder object to display all information of account with that handle
        System.out.println("Account summary: ");
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(accounttoshow.getId()).append("\n");
        sb.append("Handle: ").append(accounttoshow.getHandle()).append("\n");
        sb.append("Description: ").append(accounttoshow.getDescription()).append("\n");
        sb.append("Likes:").append(accounttoshow.getLikes()).append("\n");
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


        this.posts_list.add(post);
        account.addPost(postId); // one account can have many post
        return postId;
    }



    @Override
    public int endorsePost(String handle, int post_id) throws HandleNotRecognisedException, PostIDNotRecognisedException, NotActionablePostException {
        // Find post corresponding to the post id passed in as a parameter.
        // Loop through all posts to find matching one
        Post post_to_be_endorsed = null;
        for (Post p : posts_list) {


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
        this.posts_list.add(endorsementPost);
        endorsersaccount.getListOfPostIDsInThisAccount().add(endorsementPost.getEndorsement_post_id());

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
        for(Post findid: posts_list){
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
        CommentPost comment_post = new CommentPost(message,handle,new_Post_id,0,0,id);

        post.addChild_post_id(new_Post_id);
        post.increamentNo_of_comments_of_this_post();

        comment_post.setParentpostid(id);
        this.posts_list.add(comment_post);

        return new_Post_id;




    }

    @Override
    public void deletePost(int id) throws PostIDNotRecognisedException {
        //exceptions area starts + getting the post to be deleted
        Post post_to_be_deleted = null;
        for(Post post: posts_list){
            if(post.getPost_id() == id){
                post_to_be_deleted = post; //the post is set
            }
        }
        // Handle post exceptions
        // If post ID does not exist
        if(post_to_be_deleted == null){
            throw new PostIDNotRecognisedException("This post ID does not exist!");
        }
        //exceptions area finished
        //setting message as specification
        post_to_be_deleted.setPost_message("The original content was removed from the system and is no longer available.");
        //setting its child post to be null
        post_to_be_deleted.setChild_post_id_to_be_null();


        //---------setting the account -------------------------------------//
        Account account_selected = null;

        for (Account acc:accounts){
            if(acc.getHandle().equals(post_to_be_deleted.getAccount_handle())){
                account_selected=acc;

                List<Integer> list_postid_inThisAccount;
                list_postid_inThisAccount = account_selected.getListOfPostIDsInThisAccount();

                for (Integer ids:list_postid_inThisAccount){
                      if(id == ids){
                          acc.deletepostid(id);
                          break;
                      }
                }
                break;
            }
        }
        int number_of_endorsement = account_selected.getEndorsements();
        account_selected.setEndorsements(number_of_endorsement-1);
        //deleteing the post with the postid in the postid list within an account

        //handling the endorsement


    }


    @Override
    public String showIndividualPost (int id) throws PostIDNotRecognisedException {
        Post post_to_show= null;
        for(Post post:posts_list){
            if (post.getPost_id() == id){
                post_to_show = post;
            }
        }
        if(post_to_show == null){
            throw new PostIDNotRecognisedException("Post with id"+id+"is not found.");
        }

        StringBuilder Stringtoreturn = new StringBuilder();

        Stringtoreturn.append( "ID:").append(post_to_show.getPost_id()).append("\n");
        Stringtoreturn.append("Account:").append(post_to_show.getAccount_handle()).append("\n");
        Stringtoreturn.append("No. endorsements:").append(post_to_show.getNo_of_post_endorsements()).append(" | No. comments:").append(post_to_show.getNo_of_comments_of_the_post()).append("\n");
        Stringtoreturn.append("comment:").append(post_to_show.getPost_message());
        return Stringtoreturn.toString();
    }
    public Post returnPostById (Integer post_id) throws PostIDNotRecognisedException{
        Post postReturn = null;
        for (Post post:posts_list){
            if(post.getPost_id()==post_id){
                postReturn = post;
                return postReturn;
            }
        }
        if(postReturn == null){
            throw new PostIDNotRecognisedException("Post with id"+post_id+"is not found.");
        }
        return postReturn;
    }


    public String getPaddingByLevel(int level) {
        // helper function to add space to the front of the string
//        level += 1;
        if(level == -1){
            return "";
        }
        String padding = "\t";
        return padding.repeat(level);
//        return padding;
    }
    private boolean firsttimegetdetails= true;
    public StringBuilder getdetails(Post post_to_show, int level,boolean is_first_child) throws PostIDNotRecognisedException {
        StringBuilder stringtoreturn = new StringBuilder();
        String padding = getPaddingByLevel(level);
        String paddingminus1 = getPaddingByLevel(level-1);

        if(firsttimegetdetails == true){
            stringtoreturn.append(showIndividualPost(post_to_show.getPost_id()));
            stringtoreturn.append("\n");
            firsttimegetdetails = false;

        }
        else{
            if(is_first_child){
                stringtoreturn.append(paddingminus1+"|").append("\n");
            }
            stringtoreturn.append(paddingminus1 +"| > ");
            stringtoreturn.append("ID:").append(post_to_show.getPost_id()).append("\n");
            stringtoreturn.append(padding +  "Account:").append(post_to_show.getAccount_handle()).append("\n");
            stringtoreturn.append(padding + "No. endorsements:").append(post_to_show.getNo_of_post_endorsements()).append(" " +
                    "| No. comments:").append(post_to_show.getNo_of_comments_of_the_post()).append("\n");
            stringtoreturn.append(padding  + "comment:"+post_to_show.getPost_message());

            stringtoreturn.append("\n");
        }
        firsttimegetdetails = false;
        return stringtoreturn;
    }
    StringBuilder recursiveStringbuilder = new StringBuilder();
    public boolean dfsRecursive(int currentNode, int level, boolean is_first_child) throws PostIDNotRecognisedException {

        Post post = returnPostById(currentNode);

        List<Integer> neighbors = returnPostById(currentNode).getChild_post_id();


        if (neighbors != null) {


            recursiveStringbuilder.append(getdetails(post, level,is_first_child));
            for (int i = 0; i < neighbors.size(); i++) {
                int node = neighbors.get(i);
                if (i==0){
                    is_first_child=true;
                }else{
                    is_first_child=false;
                }


                dfsRecursive(node, level + 1, is_first_child);


            }
        }
    return false;
    }


    @Override
    public StringBuilder showPostChildrenDetails(int id) throws PostIDNotRecognisedException, NotActionablePostException {
        Post postchosen= null;
        for(Post post1:posts_list){
            if(post1.getPost_id()==id){
                postchosen =post1;
                break;
            }
        }
        if(postchosen instanceof EndorsementPost){
            throw new NotActionablePostException();
        }
        if (postchosen ==null){
            throw new PostIDNotRecognisedException("Post ID:"+id+"is not in the list.");
        }
        dfsRecursive(id,0,false);
        return recursiveStringbuilder;
    }

    @Override
    public int getMostEndorsedPost() {
        // Loop through posts in arraylist
        // Initialise attributes to store maxEndorsement and the most endorsedpost's ID
        int maxEndorsements = 0;
        int mostendorsedpostid = 0;
        Post post = null;
        for(Post find_mostendorsedpost: posts_list){
            int numEndorsements = post.getNo_of_post_endorsements();
            if(numEndorsements > maxEndorsements){
                maxEndorsements = numEndorsements;
                mostendorsedpostid = post.getPost_id();
            }
        }
        return mostendorsedpostid;
    }

    @Override
    public int getMostEndorsedAccount() {
        int maxaccountendorsements = 0;
        int mostendorsedaccountid = 0;
        // Loop through all accounts and find the one with the most endorsements
        for(Account accounts:accounts) {
            int endorsements = accounts.getEndorsements();
            if (endorsements > endorsements) {
                mostendorsedaccountid = accounts.getId();
            }
        }
        return mostendorsedaccountid;
    }

    @Override
    public void erasePlatform() {
        accounts.clear();
        posts_list.clear();
        totalPostCount = 0;
        idCounter = 0;
    }

    @Override
    public void savePlatform(String filename) throws IOException {

    }

    @Override
    public void loadPlatform(String filename) throws IOException, ClassNotFoundException {

    }

    @Override
    public int createAccount(String handle, String description) throws IllegalHandleException, InvalidHandleException {
        for (Account account : accounts) {
            if (account.getHandle().equals(handle)) {
                throw new IllegalHandleException("Handle already exists!");
            }
        }
        // Check for all other invalid input handles
        if (handle == null || handle.isEmpty() || handle.length() > 30 || handle.contains(" ")) {
            throw new InvalidHandleException("Invalid handle names. A handle should not have any whitespaces, be less than 30 characters in length and not be empty!");
        }
        // Create new account object and return ID of created account, once String handle passes exceptions

        int newId = idCounter;
        idCounter++;
        Account newAccount = new Account(newId, handle);
        accounts.add(newAccount);
        return newId;
    }

    @Override
    public void removeAccount(String handle) throws HandleNotRecognisedException {
        Account accountToRemove = null;
        // Loop through every account in arrayList and check it matches input id parameter and remove if it matches
        for (Account account : accounts)
            if (account.getHandle() == handle) {
                accountToRemove = account;
                break;
            }
        if (accountToRemove == null) {
            throw new HandleNotRecognisedException("Account with Handle: " + handle + "does not exist in the system");
        }
        accounts.remove(accountToRemove);
    }

    @Override
    public void updateAccountDescription(String handle, String description) throws HandleNotRecognisedException {
        Account account = null;
        for(Account acc: accounts){
            if(account.getHandle() == handle){
                account = acc;
            }
        }
        account.setDescription(description);
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
