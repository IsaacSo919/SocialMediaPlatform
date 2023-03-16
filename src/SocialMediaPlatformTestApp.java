import socialmedia.*;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

//		SocialMediaPlatform platform = new BadSocialMedia();

		SocialMediaPlatform platform = new SocialMedia();

		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";

		Integer id,postID;
		try {
			id = platform.createAccount("my_handle");
			id = platform.createAccount("my_handle_2");

			postID =platform.createPost("my_handle","POST 1 for handle: my_handle");
			int postID2 = platform.createPost("my_handle","POST 2 for handle: my_handle"); ;
			int endorsedId = platform.endorsePost("my_handle_2",postID);
			int commentpostID = platform.commentPost("my_handle_2", postID, "OOPPPPPP");
			platform.deletePost(postID);


			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";
			//System.out.println(platform.getNumberOfAccounts());
			platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";
			//System.out.println(platform.getNumberOfAccounts());
		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		} catch (InvalidPostException e) {
			throw new RuntimeException(e);
		} catch (HandleNotRecognisedException e) {
			throw new RuntimeException(e);
		}catch (PostIDNotRecognisedException e){
			assert(false) : "PostIDNotRecognisedException thrown incorrectly";
		} catch (NotActionablePostException e) {
			throw new RuntimeException(e);
		}

	}

}
