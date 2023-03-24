import socialmedia.*;

import java.util.ArrayList;
import java.util.List;

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


		try {
			//create accounts
			Integer id1 = platform.createAccount("my_handle_1");
			Integer id2 = platform.createAccount("my_handle_2");
			Integer id3 = platform.createAccount("my_handle_3");
			Integer id4 = platform.createAccount("my_handle_4");
			Integer id5 = platform.createAccount("my_handle_5");

//			//-------------------------------------------------------------------------------------------------------
//			//error post message >100 characters
//			StringBuilder sb = new StringBuilder();
//			String repeatedStr = "abcde";
//			int repeatCount = 25;
//
//			for (int i = 0; i < repeatCount; i++) {
//				sb.append(repeatedStr);
//			}
//			String commentpostmessage = sb.toString();

//			//-------------------------------------------------------------------------------------------------------

			//create Post
			int postID1 = platform.createPost("my_handle_1","I like examples.");
			int postID2 = platform.createPost("my_handle_2","POST 2 for handle: my_handle");
			int commentpostID3 = platform.commentPost("my_handle_2", postID1, "No more than me...");
			int commentpostID4 = platform.commentPost("my_handle_3", postID1, "Can't you do better than this?");
			int commentpostID5 = platform.commentPost("my_handle_1", commentpostID3, "I can prove!");
			int commentpostID6 = platform.commentPost("my_handle_2", commentpostID5 , "Prove it");
			int commentpostID7 = platform.commentPost("my_handle_5", postID1 , "Where is the example?");
			int endosementpostID8 = platform.endorsePost("my_handle_4",postID2);
			int endosementpostID9 = platform.endorsePost("my_handle_5",postID1);
			int endosementpostID10 = platform.commentPost("my_handle_1",commentpostID7,"This is the example!");
			//File IO//
			platform.savePlatform("");
			//showPostChildrenDetails
//			System.out.println(platform.showPostChildrenDetails(postID1));

//			//printing after commenting
//			System.out.println("postID:"+postID);
//			for (Post post : ((SocialMedia) platform).posts_list){
//				if (post.getPost_id() == commentpostID){
//					System.out.println("comment_post ID:" + post.getPost_id());
//					System.out.println("post message: "+post.getPost_message());
//					System.out.println("account handle: "+post.getAccount_handle());
//					int parentpostid = ((CommentPost) post).getParentpostid();
//					System.out.println("parentpostid: "+ parentpostid);
//					break;
//				}
//			}

//			//deleting the first post
//			System.out.println("TotalOriginalPost before deletion:"+platform.getTotalOriginalPosts());
//			platform.deletePost(commentpostID4);
//			System.out.println("TotalOriginalPost after deletion:"+platform.getTotalOriginalPosts());

//			//show individual post
//			System.out.println(platform.showIndividualPost(postID2));

			////printing all accounts
//			for (Account acc:((SocialMedia) platform).getAccounts()){
//				if(acc.getHandle()=="my_handle"){
//					System.out.println("Post list of this account"+acc.getListOfPostIDsInThisAccount());
//				}
//			}

//			//printing after deleteing - post and account
//			for (Post post : ((SocialMedia) platform).posts_list){
//				if (post.getPost_id() == postID){
//
//					post_delete = post;
//					System.out.println("PostId:" + post.getPost_id());
//					System.out.println("post message: "+post.getPost_message());
//					System.out.println("account handle: "+post.getAccount_handle());
//					System.out.println("child's post's id(should be null):"+post.getChild_post_id());
//				}
//			}
//			List<Integer> postArrayList = new ArrayList<>();
//			for (Account account: ((SocialMedia) platform).accounts){
//				if(post_delete.getAccount_handle().equals(account.getHandle())){
//
//					postArrayList = account.getListOfPostIDsInThisAccount();
//					System.out.println("postArraylist"+postArrayList);
//					break;
//				}
//			}
//			//printing after endorsement
//			System.out.println("endosedID:"+endorsedId);
//			for (Post post : ((SocialMedia) platform).posts_list){
//				if (post.getPost_id() == endorsedId){
//					System.out.println("post_id that equals endorsed ID:" + post.getPost_id());
//					System.out.println("post message: "+post.getPost_message());
//					System.out.println("account handle: "+post.getAccount_handle());
//					int endormentId = ((EndorsementPost) post).getEndorsement_post_id();
//					System.out.println("endorsmentId: "+ endormentId);
//				}
//			}
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";
			//System.out.println(platform.getNumberOfAccounts());
			platform.removeAccount(100);
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
