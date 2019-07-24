package com.example.diko.Class;

import com.google.gson.annotations.SerializedName;

public class Result {
        @SerializedName("Message")
        private String message;
        @SerializedName("user")
        private User user;
        public Result( String message, User user) {
            this.message = message;
            this.user = user;
        }
        public String getMessage() {
            return message;
        }
        public User getUser() {
            return user;

    }
}
