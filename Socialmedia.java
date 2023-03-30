import ...;

public class Socialmedia {
    public static void main(String[] args) throws Exception{
        System.out.println("jdkfa");
        SocialMedia platform = new SocialMedia();
        platform.loadPlatform("filename");
        System.out.println("hello");
        System.out.println("Number of accounts:");
        System.out.println(platform.getNumberOfAccounts());
        System.out.println("Number of endorsements: ");
        System.out.println(platform.getTotalEndorsmentPosts());
        System.out.println("Total comments: ");
        System.out.println(platform.getTotalCommentPosts());
    }
}
