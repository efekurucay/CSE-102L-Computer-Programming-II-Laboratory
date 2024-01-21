import java.util.*;

public class Ex10_20210808053 {
    public static void main(String[] args) {

    }
}
class User{
    private int id;
    private String username;
    private String email;
    private Set<User> followers;
    private Set<User> following;
    private Set<User> likedPosts;
    private Map<User, Queue<Message>> messages;

    User(String username, String  email){
        this.username = username;
        this.email = email;
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
        this.likedPosts = new HashSet<>();
        this.messages = new HashMap<>();
        this.id = hashCode();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public Set<User> getLikedPosts() {
        return likedPosts;
    }

    public void message(User recipient, String content) {
        if (!messages.containsKey(recipient)) {
            messages.put(recipient, new LinkedList<>());
            recipient.messages.put(this, new LinkedList<>());
        }

        Message message = new Message(this, content);
        messages.get(recipient).add(message);
        recipient.messages.get(this).add(message);

        read(recipient);
    }
    public void read(User user){
        System.out.println(user.messages);
    }
    public void follow(User user){
        if(following.contains(user))
            following.remove(user);
        else
            following.add(user);
    }
    public void like(Post post){
        post.likedBy(this);
    }
    public Post post(String content){
        return new Post(content);
    }
    public Comment comment(Post post, String content){
        Comment comment = new Comment(content);
        post.commentBy(this, comment);
        return comment;
    }
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return this.id == other.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
class Message{
    private boolean seen;
    private java.util.Date dateSent;
    private String content;
    private User sender;

    Message(User sender,String content){
        this.sender = sender;
        this.content = content;
        this.dateSent = new java.util.Date();
        this.seen = false;
    }
    public String read(User reader){
        if (!sender.equals(reader)) {
            seen = true;
        }
        System.out.println("Sent at: " + dateSent);
        return content;
    }
    public boolean hasRead(){
        return seen;
    }
}
class Post{
    private java.util.Date datePosted;
    private String content;
    private Set<User> likes;
    private Map<User, ArrayList<Comment>> comments;

    Post(String content){
        this.datePosted = new java.util.Date();
        this.content = content;
        this.likes = new HashSet<>();
        this.comments = new HashMap<>();

    }
    public boolean likedBy(User user){
        if(!likes.contains(user)){
            likes.add(user);
            return true;
        }
        likes.remove(user);
        return false;
    }
    public void commentBy(User user, Comment comment){
        comments.get(user).add(comment);////
    }
    public String getContent(){
        System.out.println("Posted at: " + datePosted);
        return content;
    }
    public Comment getComment(User user, int index){
        if(comments.get(user).size() < index)
            return null;
        return comments.get(user).get(index);
    }
    public int getCommentCount(){
        int total = 0;
        for(Map.Entry<User, ArrayList<Comment>> entry: comments.entrySet()) {
            User u = entry.getKey();
            int a = comments.get(u).size();
            total += a;
        }
        return total;
    }

    public int getCommentCountByUser(User user){
        return comments.get(user).size();
    }
}
class Comment extends Post{

    Comment(String content) {
        super(content);
    }
}
class SocialNetwork{
    private static Map<User, ArrayList<Post>> postsByUsers = new HashMap<>();

    public static User register (String username, String email){
        User user = new User(username, email);
        if (!postsByUsers.containsKey(user)) {
            postsByUsers.put(user, new ArrayList<>());
            return user;
        }
        return null;
    }
    public static Post post(User user, String content){
        if(postsByUsers.containsKey(user)){
            Post post = new Post(content);
            postsByUsers.get(user).add(post);
            return post;
        }
        return null;
    }
    public static User getUser(String email){
        int hashCode = Objects.hash(email);
        for (User user : postsByUsers.keySet()) {
            if (user.hashCode() == hashCode) {/////////////
                return user;
            }
        }
        return null;
    }
    public static Set<Post> getFeed(User user){
        Set<Post> feed = new HashSet<>();
        for (User followedUser : user.getFollowing()) {
            if (postsByUsers.containsKey(followedUser)) {
                List<Post> followedUserPosts = postsByUsers.get(followedUser);
                feed.addAll(followedUserPosts);
            }
        }
        return feed;
    }
    public static Map<User, String> search(String keyword){
        Map<User, String> searchResults = new HashMap<>();
        for (User user : postsByUsers.keySet()) {
            if (user.getUsername().contains(keyword)) {
                searchResults.put(user, user.getUsername());
            }
        }
        return searchResults;
    }
    public static <K, V> Map<V, Set<K>> reverseMap(Map<K,V> map){
        Map<V, Set<K>> reversedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();

            if (!reversedMap.containsKey(value)) {
                reversedMap.put(value, new HashSet<>());
            }

            Set<K> keySet = reversedMap.get(value);
            keySet.add(key);
        }

        return reversedMap;
    }
}