package com.example.konrad.apzumiproject;

/**
 * Created by Konrad on 08.08.2018.
 */

public class APIResults {
    public String userName;
    public String repositoryName;
    public String avatarSource;
    public boolean isBitBucketResource;

    public APIResults(String userName, String repositoryName, String avatarSource, boolean isBitBucketResource) {
        this.userName = userName;
        this.repositoryName = repositoryName;
        this.avatarSource = avatarSource;
        this.isBitBucketResource = isBitBucketResource;
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
