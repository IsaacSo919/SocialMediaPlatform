package socialmedia;

import java.util.ArrayList;
import java.util.List;

public class Post{

    private String account_handle;
    //private int post_count;
    private String post_message;
    private int post_id;
    private int likes;

    private int no_of_post_endorsements;
    private int no_of_comments_in_the_post;
    private List<Integer> integerListofEndorsmentID = new ArrayList<>();
    private List<Integer> child_post_id = new ArrayList<>();



    public void setAccount_handle(String account_handle) {
        this.account_handle = account_handle;
    }

//    public int getPost_count() {
//        return post_count;
//    }

//    public void increment_post_count(){
//        post_count++;
//    }

    public Post(){}



    public Post(String post_message, String account_handle, int post_id, int likes, int no_of_post_endorsements ){
        this.post_message = post_message;
        this.account_handle = account_handle;
        this.post_id = post_id;
        this.likes = likes;
        this.no_of_post_endorsements = no_of_post_endorsements;

    }


//    public Post(int post_count, String post_message,int post_id,int likes,int post_endorsements){
//        this.post_count = post_count;
//        this.post_message = post_message;
//        this.post_id = post_id;
//        this.likes = likes;
//
//
//
//    }


    public Post(int post_id, String message,int accountId){
        this.setPost_id(getPost_id());
        this.setPost_message(getPost_message());
        this.setLikes(0);
    }






    /**getters*/
    public String getAccount_handle() {
        return account_handle;
    }
    public String getPost_message() {
        return post_message;
    }


    public int getPost_id() {
        return post_id;
    }

    public List<Integer> getChild_post_id() {
        return child_post_id;
    }


    public int getNo_of_post_endorsements() {
        return no_of_post_endorsements;
    }

    public int getNo_of_comments_of_the_post() {
        return no_of_comments_in_the_post;
    }

    public int getLikes() {
        return likes;
    }
    /* setter*/
    public void setPost_message(String post_message) {
        this.post_message = post_message;
    }
    public void addChild_post_id(Integer child_post_id) {
        this.child_post_id.add(child_post_id);
    }

    public void setChild_post_id_to_be_null() {
        this.child_post_id= null;
    }

    public void setNo_of_post_endorsements(int no_of_post_endorsements){
        this.no_of_post_endorsements = no_of_post_endorsements;
    }
    public void increamentNo_of_comments_of_this_post(){
        this.no_of_comments_in_the_post++;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void increment_no_of_comments_of_this_post(CommentPost commentPost){
        no_of_comments_in_the_post++;
    }



}




