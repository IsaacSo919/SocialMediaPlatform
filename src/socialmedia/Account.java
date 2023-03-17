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
    private int no_of_comments;


    private List<Integer> list_of_postIDs_in_this_account = new ArrayList<>();




    public void increment_account_endorsements(){
        endorsements++;
    }

    public void setNo_of_comments(int no_of_comments) {
        this.no_of_comments = no_of_comments;
    }


    public  List<Integer> getListOfPostIDsInThisAccount() {
        if(list_of_postIDs_in_this_account == null){
            return new ArrayList<>();
        }
        return list_of_postIDs_in_this_account;
    }
    public Account(String handle, int endorsements, String description) {
        super();
        this.handle = handle;
        this.endorsements = endorsements;
        this.list_of_postIDs_in_this_account = new ArrayList<Integer>();// Initalise arraylist
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
        return this.getListOfPostIDsInThisAccount().size();
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
    public void setId(int id) {
        this.id = id;
    }

    public void setEndorsements(int endorsements) {
        this.endorsements = endorsements;
    }

    public void setList_of_postIDs_in_this_account(List<Integer> list_of_postIDs_in_this_account){
        this.list_of_postIDs_in_this_account = list_of_postIDs_in_this_account;
    }


    public void setCount(int count) {
        this.count = count;
    }

    public void addPost(int postid){
        list_of_postIDs_in_this_account.add(postid);
//        post.incrementpost_count();

    }
    public void deletepostid(int id){
        for (Integer id_in_list: list_of_postIDs_in_this_account)
            if (id_in_list==id){
                list_of_postIDs_in_this_account.remove(id_in_list);

            }
    }



}