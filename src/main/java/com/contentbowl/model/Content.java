package com.contentbowl.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A content inside Content Bowl
 * 
 * @author <a href="mailto:viveiros@ciandt.com">Daniel Viveiros</a>
 */
public class Content {
    
    public static final String TYPE_ARTICLE = "A";
    public static final String TYPE_IMAGE = "I";
    public static final String TYPE_VIDEO = "V";
    public static final String TYPE_LINK = "L";
    public static final String TYPE_AUDIO = "D";
    
    //Unique ID: key
    private String id;
    private String contentGroup; //if the content belongs to a specific group, playlist, community etc
    
    //Content
    private String title;
    private String summary;
    private String content;
    private List<String> contentTags;
    private List<String> extraTags;
    private String type;
    
    //Provider information
    private String providerMnemonic;
    private String providerUserId;
    private String providerContentId;
    private String providerContentURL;
    private Date providerUpdated;
    private Date providerPublished;

    //Author information
    private String authorId;
    private String authorDisplayName;
    private String authorImageURL;
    private String authorReference;
    
    //Relevant dates
    private Date createdAt;
    private Date updated;    
    
    //i18n
    private List<String> languages;
    private List<String> regions;
    private String detectedLanguage;
    
    //Geo information
    private String geoCode;
    private String address;
    private String placeName;
    
    //attachment information
    private Map<String, String> attachmentData;
    
    //generic data
    private Map<String, String> specificData;
    
    public Content() {
        contentTags = new ArrayList<String>();
        extraTags = new ArrayList<String>();
        languages = new ArrayList<String>();
        regions = new ArrayList<String>();
        attachmentData = new HashMap<String, String>();
        specificData = new HashMap<String, String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getContentTags() {
        return contentTags;
    }

    public void setContentTags(List<String> contentTags) {
        this.contentTags = contentTags;
    }

    public List<String> getExtraTags() {
        return extraTags;
    }

    public void setExtraTags(List<String> extraTags) {
        this.extraTags = extraTags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProviderMnemonic() {
        return providerMnemonic;
    }

    public void setProviderMnemonic(String providerMnemonic) {
        this.providerMnemonic = providerMnemonic;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getProviderContentId() {
        return providerContentId;
    }

    public void setProviderContentId(String providerContentId) {
        this.providerContentId = providerContentId;
    }

    public String getProviderContentURL() {
        return providerContentURL;
    }

    public void setProviderContentURL(String providerContentURL) {
        this.providerContentURL = providerContentURL;
    }

    public Date getProviderUpdated() {
        return providerUpdated;
    }

    public void setProviderUpdated(Date providerUpdated) {
        this.providerUpdated = providerUpdated;
    }

    public Date getProviderPublished() {
        return providerPublished;
    }

    public void setProviderPublished(Date providerPublished) {
        this.providerPublished = providerPublished;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorDisplayName() {
        return authorDisplayName;
    }

    public void setAuthorDisplayName(String authorDisplayName) {
        this.authorDisplayName = authorDisplayName;
    }

    public String getAuthorImageURL() {
        return authorImageURL;
    }

    public void setAuthorImageURL(String authorImageURL) {
        this.authorImageURL = authorImageURL;
    }

    public String getAuthorReference() {
        return authorReference;
    }

    public void setAuthorReference(String authorReference) {
        this.authorReference = authorReference;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    public String getDetectedLanguage() {
        return detectedLanguage;
    }

    public void setDetectedLanguage(String detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }

    public String getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getContentGroup() {
        return contentGroup;
    }

    public void setContentGroup(String contentGroup) {
        this.contentGroup = contentGroup;
    }
    
    public void addContentTag( String tag ) {
        this.contentTags.add( tag );
    }
    
    public void addExtraTag( String tag ) {
        this.extraTags.add( tag );
    }
    
    public void addLanguage( String language ) {
        this.languages.add( language );
    }
    
    public void addRegion( String region ) {
        this.regions.add( region );
    }
    
    public List<String> getTags() {
        List<String> allTags = new ArrayList<String>();
        allTags.addAll( getContentTags() );
        allTags.addAll( getExtraTags() );
        return allTags;
    }
    
    public void addAttachmentData( String key, String value ) {
        this.attachmentData.put( key, value );
    }
    
    public void addSpecificData( String key, String value ) {
        this.specificData.put( key, value );
    }
    
    public String getAttachmentData( String key ) {
        return this.attachmentData.get(key);
    }
    
    public String getSpecificData( String key ) {
        return this.specificData.get(key);
    }
}
