package com.example.konrad.apzumiproject;

/**
 * Created by Konrad on 08.08.2018.
 */

public class APIResults {
    public String userName;
    public String repositoryName;
    public String avatarSource;
    public boolean isBitBucketResource;
    public String description;

    public APIResults(String userName, String repositoryName, String avatarSource, boolean isBitBucketResource, String description) {
        this.userName = userName;
        this.repositoryName = repositoryName;
        this.avatarSource = avatarSource;
        this.isBitBucketResource = isBitBucketResource;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getAvatarSource() {
        return avatarSource;
    }

    public void setAvatarSource(String avatarSource) {
        this.avatarSource = avatarSource;
    }

    public boolean isBitBucketResource() {
        return isBitBucketResource;
    }

    public void setBitBucketResource(boolean bitBucketResource) {
        isBitBucketResource = bitBucketResource;
    }
}
