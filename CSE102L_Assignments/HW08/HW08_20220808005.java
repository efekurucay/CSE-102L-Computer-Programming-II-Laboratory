import java.util.*;

/**---------------------------------------------------
 * Akdeniz University CSE102L Assignments
 * author: Yahya Efe Kuruçay
 * since: 25.05.2024
 * Description: Homework08
 * Score: ?
 * Website: https://efekurucay.com
*---------------------------------------------------*/

/***
 *    ░▒▓████████▓▒░▒▓████████▓▒░▒▓████████▓▒░ 
 *    ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░        
 *    ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░        
 *    ░▒▓██████▓▒░ ░▒▓██████▓▒░ ░▒▓██████▓▒░   
 *    ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░        
 *    ░▒▓█▓▒░      ░▒▓█▓▒░      ░▒▓█▓▒░        
 *    ░▒▓████████▓▒░▒▓█▓▒░      ░▒▓████████▓▒░ 
 *                                             
 *                                             
 */


public class HW08_20220808005 {

    public static void main(String[] args) {
       
        User user1 = SocialNetwork.register("efekurucay24", "contact@efekurucay.com");
        User user2 = SocialNetwork.register("efegsm_", "efegsm2022@gmail.com");
        User user3 = SocialNetwork.register("hsdakdeniz", "hsdakdeniz@hotmail.com");

        System.out.println("Users registered.");

        user1.follow(user2);
        user2.follow(user3);
        user1.follow(user3);
        System.out.println("Following relationships established.");

        Post post1 = SocialNetwork.post(user2, "efegsm_'s first post");
        Post post2 = SocialNetwork.post(user3, "hsdakdeniz's first post");
        System.out.println("Posts created.");

        user1.like(post1);
        user1.like(post2);
        user2.like(post2);
        System.out.println("Posts liked.");

        user1.comment(post1, "Great post!");
        user2.comment(post2, "Nice one!");
        System.out.println("Comments added.");

        user1.message(user2, "Hello efegsm_!");
        user2.message(user1, "Hello efekurucay24!");
        user3.message(user1, "Hi efekurucay24!");
        System.out.println("Messages sent.");

        user1.read(user2);
        user1.read(user3);
        System.out.println("Messages read.");

        System.out.println("Feed for User1:");
        Set<Post> feed = SocialNetwork.getFeed(user1);
        for (Post post : feed) {
            System.out.println(post.getContent());
        }

        System.out.println("Search results for 'user':");
        Map<User, String> searchResults = SocialNetwork.search("user");
        for (Map.Entry<User, String> entry : searchResults.entrySet()) {
            System.out.println(entry.getValue());
        }

        Map<User, List<Post>> postsByUsers = new HashMap<>();
        postsByUsers.put(user1, Arrays.asList(post1, post2));
        Map<List<Post>, Set<User>> reversedMap = SocialNetwork.reverseMap(postsByUsers);
        System.out.println("Reversed map:");
        for (Map.Entry<List<Post>, Set<User>> entry : reversedMap.entrySet()) {
            System.out.println("Posts: " + entry.getKey() + " Users: " + entry.getValue());
        }

    }
}
    
class User {
        private int id;
        private String username;
        private String email;
        private Set<User> followers;
        private Set<User> following;
        private Set<Post> likedPosts;
        private Map<User, Queue<Message>> messages;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
            this.id = this.hashCode();
            this.followers = new HashSet<>();
            this.following = new HashSet<>();
            this.likedPosts = new HashSet<>();
            this.messages = new HashMap<>();
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

        public Set<Post> getLikedPosts() {
            return likedPosts;
        }

        public void message(User recipient, String content) {
            if (!this.messages.containsKey(recipient)) {
                this.messages.put(recipient, new LinkedList<>());
            }
            if (!recipient.messages.containsKey(this)) {
                recipient.messages.put(this, new LinkedList<>());
            }
            Message newMessage = new Message(this, content);
            this.messages.get(recipient).add(newMessage);
            recipient.messages.get(this).add(newMessage);
            this.read(recipient);
        }

        public void read(User user) {
            Queue<Message> queue = this.messages.get(user);
            if (queue != null) {
                for (Message message : queue) {
                    System.out.println(message.read(this));
                }
            }
        }

        public void follow(User user) {
            if (this.following.contains(user)) {
                this.following.remove(user);
                user.followers.remove(this);
            } else {
                this.following.add(user);
                user.followers.add(this);
            }
        }

        public void like(Post post) {
            if (this.likedPosts.contains(post)) {
                this.likedPosts.remove(post);
                post.likedBy(this);
            } else {
                this.likedPosts.add(post);
                post.likedBy(this);
            }
        }

        public Post post(String content) {
            return new Post(content);
        }

        public Comment comment(Post post, String content) {
            Comment newComment = new Comment(content);
            post.commentBy(this, newComment);
            return newComment;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            User user = (User) obj;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(email);
        }
    }

class Message {
        private boolean seen;
        private Date dateSent;
        private String content;
        private User sender;

        public Message(User sender, String content) {
            this.sender = sender;
            this.content = content;
            this.dateSent = new Date();
            this.seen = false;
        }

        public String read(User reader) {
            if (!sender.equals(reader)) {
                this.seen = true;
            }
            System.out.println("Sent at: " + dateSent);
            return content;
        }

        public boolean hasRead() {
            return seen;
        }
    }
 
class Post {
        private Date datePosted;
        private String content;
        private Set<User> likes;
        private Map<User, List<Comment>> comments;

        public Post(String content) {
            this.datePosted = new Date();
            this.content = content;
            this.likes = new HashSet<>();
            this.comments = new HashMap<>();
        }

        public boolean likedBy(User user) {
            if (!likes.add(user)) {
                likes.remove(user);
                return false;
            }
            return true;
        }

        public boolean commentBy(User user, Comment comment) {
            comments.computeIfAbsent(user, k -> new ArrayList<>()).add(comment);
            return true;
        }

        public String getContent() {
            System.out.println("Posted at: " + datePosted);
            return content;
        }

        public Comment getComment(User user, int index) {
            List<Comment> userComments = comments.get(user);
            if (userComments != null && index < userComments.size()) {
                return userComments.get(index);
            }
            return null;
        }

        public int getCommentCount() {
            return comments.values().stream().mapToInt(List::size).sum();
        }

        public int getCommentCountByUser(User user) {
            List<Comment> userComments = comments.get(user);
            return userComments != null ? userComments.size() : 0;
        }
    }

class Comment extends Post {
        public Comment(String content) {
            super(content);
        }
    }

class SocialNetwork {
        private static Map<User, List<Post>> postsByUsers = new HashMap<>();

        public static User register(String username, String email) {
            User newUser = new User(username, email);
            if (!postsByUsers.containsKey(newUser)) {
                postsByUsers.put(newUser, new ArrayList<>());
                return newUser;
            }
            return null;
        }

        public static Post post(User user, String content) {
            if (postsByUsers.containsKey(user)) {
                Post newPost = new Post(content);
                postsByUsers.get(user).add(newPost);
                return newPost;
            }
            return null;
        }

        public static User getUser(String email) {
            int emailHash = Objects.hash(email);
            for (User user : postsByUsers.keySet()) {
                if (user.hashCode() == emailHash) {
                    return user;
                }
            }
            return null;
        }

        public static Set<Post> getFeed(User user) {
            Set<Post> feed = new HashSet<>();
            for (User following : user.getFollowing()) {
                feed.addAll(postsByUsers.get(following));
            }
            return feed;
        }

        public static Map<User, String> search(String keyword) {
            Map<User, String> result = new HashMap<>();
            for (User user : postsByUsers.keySet()) {
                if (user.getUsername().contains(keyword)) {
                    result.put(user, user.getUsername());
                }
            }
            return result;
        }

        public static <K, V> Map<V, Set<K>> reverseMap(Map<K, V> map) {
            Map<V, Set<K>> reversedMap = new HashMap<>();
            for (Map.Entry<K, V> entry : map.entrySet()) {
                reversedMap.computeIfAbsent(entry.getValue(), k -> new HashSet<>()).add(entry.getKey());
            }
            return reversedMap;
        }
    }

/***
 *              _____                    _____                    _____          
 *             /\    \                  /\    \                  /\    \         
 *            /::\    \                /::\    \                /::\    \        
 *           /::::\    \              /::::\    \              /::::\    \       
 *          /::::::\    \            /::::::\    \            /::::::\    \      
 *         /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \     
 *        /:::/__\:::\    \        /:::/__\:::\    \        /:::/__\:::\    \    
 *       /::::\   \:::\    \      /::::\   \:::\    \      /::::\   \:::\    \   
 *      /::::::\   \:::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \  
 *     /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \ 
 *    /:::/__\:::\   \:::\____\/:::/  \:::\   \:::\____\/:::/__\:::\   \:::\____\
 *    \:::\   \:::\   \::/    /\::/    \:::\   \::/    /\:::\   \:::\   \::/    /
 *     \:::\   \:::\   \/____/  \/____/ \:::\   \/____/  \:::\   \:::\   \/____/ 
 *      \:::\   \:::\    \               \:::\    \       \:::\   \:::\    \     
 *       \:::\   \:::\____\               \:::\____\       \:::\   \:::\____\    
 *        \:::\   \::/    /                \::/    /        \:::\   \::/    /    
 *         \:::\   \/____/                  \/____/          \:::\   \/____/     
 *          \:::\    \                                        \:::\    \         
 *           \:::\____\                                        \:::\____\        
 *            \::/    /                                         \::/    /        
 *             \/____/                                           \/____/         
 *                                                                               
 */