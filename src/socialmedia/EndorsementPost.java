package socialmedia;

public class EndorsementPost extends Post {

    // post id
    // message
    private int endorsement_post_id;
    private int parent_post_id;

    public int getEndorsement_post_id() {
        return endorsement_post_id;
    }

    public void setEndorsement_post_id(int endorsement_post_id) {
        this.endorsement_post_id = endorsement_post_id;
    }


    public EndorsementPost(int post_id, String handle, int endorsement_post_id, String message) {
        super();
        this.setPost_id(post_id);
        this.setAccount_handle(handle);
        this.setPost_message(message);
        this.endorsement_post_id = endorsement_post_id;
    }
}
