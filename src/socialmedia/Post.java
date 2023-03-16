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
    private int No_of_comments_of_this_post;
    private List<CommentPost> CommentPosts_of_that_Post = new ArrayList();
    private List<EndorsementPost> endorsementPosts_of_that_Post;



    public Post(){

    }

    public String getAccount_handle() {
        return account_handle;
    }

    public void setAccount_handle(String account_handle) {
        this.account_handle = account_handle;
    }

//    public int getPost_count() {
//        return post_count;
//    }

//    public void increment_post_count(){
//        post_count++;
//    }


    public Post(String post_message,String account_handle, int post_id, int likes, int no_of_post_endorsements ){
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
//    public void incrementpost_count(){
//        post_count++;
//    }

    public Post(int post_id, String message,int accountId){
        this.setPost_id(getPost_id());
        this.setPost_message(getPost_message());
        this.setLikes(0);
    }



    public String getPost_message() {
        return post_message;
    }

//    public void setPost_count(int post_count) {
//        this.post_count = post_count;
//    }

    public void setPost_message(String post_message) {
        this.post_message = post_message;
    }
    public void setNo_of_post_endorsements(int no_of_post_endorsements){
        this.no_of_post_endorsements = no_of_post_endorsements;
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
    public void addCommentPostinPostList(CommentPost commentPost){
        CommentPosts_of_that_Post.add(commentPost);
        No_of_comments_of_this_post++;
    }
    public List<CommentPost> getCommentPosts() {
        return CommentPosts_of_that_Post;
    }
    public int no_of_comment_posts_of_this_post(){
        return CommentPosts_of_that_Post.size();
    }


    public void addEndorsementPost(EndorsementPost endorsementPost){
        endorsementPosts_of_that_Post.add(endorsementPost);
        no_of_post_endorsements++;
    }



    public List<EndorsementPost> getEndorsementPosts() {
        return endorsementPosts_of_that_Post;
    }
}




