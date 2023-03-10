package socialmedia;
import java.util.List;
import java.util.ArrayList;

public class Account {
    private int id;
    private String handle;
    private int likes;
    public int count = 0;

    private String description;

    private int endorsements;

    private List<Post> posts;



    public void increment_account_endorsements(){
        endorsements++;
    }



    public void setId(int id) {
        this.id = id;
    }

    public  List<Post> getPosts() {
        if(posts == null){
            return new ArrayList<>();
        }
        return posts;
    }
    public Account(String handle, int endorsements, String description) {
        super();
        this.handle = handle;
        this.endorsements = endorsements;
        this.likes = 0;
        this.posts = new ArrayList<Post>();// Initalise arraylist
        this.description = description;
        this.id = ++count;
    }

    public Account(int id, String handle) {
        this.id = id;
        this.handle = handle;
        this.description = " ";
    }

    public int getId() {
        return this.id;
    }
    public int getPost_count() {
        return this.getPosts().size();
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEndorsements() {
        return endorsements;
    }

    public void setEndorsements(int endorsements) {
        this.endorsements = endorsements;
    }

    public void setPosts(List<Post> posts){
        this.posts = posts;
    }
    public void addPost(Post post){
        if(posts == null){
            posts = new ArrayList<>();
        }
        posts.add(post);
        post.incrementpost_count();

    }

}