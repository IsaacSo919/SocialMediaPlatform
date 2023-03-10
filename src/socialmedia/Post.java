package socialmedia;

public class Post{

    private String account_handle;
    private int post_count;
    private String post_message;
    private int post_id;
    private int likes;

    public Post(){

    }

    public String getAccount_handle() {
        return account_handle;
    }

    public void setAccount_handle(String account_handle) {
        this.account_handle = account_handle;
    }

    public int getPost_count() {
        return post_count;
    }

    public void increment_post_count(){
        post_count++;
    }




    public Post(int post_count, String post_message,int post_id,int likes,int post_endorsements){
        this.post_count = post_count;
        this.post_message = post_message;
        this.post_id = post_id;
        this.likes = likes;



    }
    public void incrementpost_count(){
        post_count++;
    }

    public Post(int post_id, String message,int accountId){
        this.setPost_id(getPost_id());
        this.setPost_message(getPost_message());
        this.setLikes(0);
    }



    public String getPost_message() {
        return post_message;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    public void setPost_message(String post_message) {
        post_message = "";
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


}
