import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user
 * and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Your design should support the following methods:
 *
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
 *                      Each item in the news feed must be posted by users who the user followed or by the user
 *                      herself. Tweets must be ordered from most recent to least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * Example:
 *
 * Twitter twitter = new Twitter();
 *
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 *
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 *
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 *
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 *
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 */

class Tweet {
    public int id;
    public long timestamp;
}

public class Twitter {
    private static final int NUM_TWEETS = 10;

    private Map<Integer, Set<Integer>> followees;
    private Map<Integer, List<Tweet>> tweets;
    private long currentTime;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        followees = new HashMap<>();
        tweets = new HashMap<>();

        currentTime = 0;
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet();
        tweet.id = tweetId;
        tweet.timestamp = currentTime++;

        if (!tweets.containsKey(userId)) {
            tweets.put(userId, new LinkedList<>());
        }
        tweets.get(userId).add(0, tweet);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> queue = new PriorityQueue<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                long timestamp1 = ((Tweet) o1).timestamp;
                long timestamp2 = ((Tweet) o2).timestamp;
                if (timestamp1 < timestamp2) {
                    return 1;
                } else if (timestamp1 == timestamp2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        if (tweets.containsKey(userId)) {
            for (int i = 0; i < tweets.get(userId).size() && i < NUM_TWEETS; i++) {
                queue.add(tweets.get(userId).get(i));
            }
        }

        if (followees.containsKey(userId)) {
            for (int followee : followees.get(userId)) {
                if (followee == userId || !tweets.containsKey(followee)) {
                    continue;
                }

                for (int i = 0; i < tweets.get(followee).size() && i < NUM_TWEETS; i++) {
                    queue.add(tweets.get(followee).get(i));
                }
            }
        }

        List<Integer> newsFeed = new ArrayList<>();
        for (int i = 0; !queue.isEmpty() && i < NUM_TWEETS; i++) {
            newsFeed.add(queue.poll().id);
        }

        return newsFeed;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!followees.containsKey(followerId)) {
            followees.put(followerId, new HashSet<>());
        }
        followees.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followees.containsKey(followerId) && followees.get(followerId).contains(followeeId)) {
            followees.get(followerId).remove(new Integer(followeeId));
        }
    }
}
