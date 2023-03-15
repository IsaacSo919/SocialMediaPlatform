package socialmedia;

public class CommentPost extends Post{
    private int parentpostid;


    public int getParentpostid() {
        return parentpostid;
    }



    public CommentPost(String post_message, String account_handle, int post_id, int likes, int no_of_post_endorsements, int parentpostid){
        super();
        this.setPost_message(post_message);
        this.setAccount_handle(account_handle);
        this.setPost_id(post_id);
        this.setLikes(likes);
        this.setNo_of_post_endorsements(no_of_post_endorsements);
        this.parentpostid = parentpostid;

    }




}
