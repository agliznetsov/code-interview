package code.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DesignTwitter {

    static class Twitter {
        private static class Tweet {
            int userId;
            int tweetId;

            public Tweet(int userId, int tweetId) {
                this.userId = userId;
                this.tweetId = tweetId;
            }
        }

        private List<Tweet> tweets = new ArrayList<>();
        private Map<Integer,Set<Integer>> follows = new HashMap<>();


        public void postTweet(int userId, int tweetId) {
            tweets.add(new Tweet(userId, tweetId));
        }

        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new ArrayList<>(10);
            Set<Integer> following = follows.get(userId);
            for (int i = tweets.size() - 1; i >= 0 && res.size() < 10; i--) {
                Tweet tweet = tweets.get(i);
                if (tweet.userId == userId || (following != null && following.contains(tweet.userId))) {
                    res.add(tweet.tweetId);
                }
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            follows.computeIfAbsent(followerId, it -> new HashSet<>()).add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            follows.computeIfAbsent(followerId, it -> new HashSet<>()).remove(followeeId);
        }
    }

}
