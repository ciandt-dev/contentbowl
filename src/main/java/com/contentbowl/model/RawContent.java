package com.contentbowl.model;

/**
 * Raw content saved from the original source. This entity is more to backup some data and
 * enable to create some scripts to process it 
 * 
 * @author <a href="mailto:viveiros@ciandt.com">Daniel Viveiros</a>
 */
public class RawContent {
    
    private String id;
    private String providerMnemonic;
    private Long streamId;
    private String content;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getProviderMnemonic() {
        return providerMnemonic;
    }
    public void setProviderMnemonic(String providerMnemonic) {
        this.providerMnemonic = providerMnemonic;
    }
    public Long getStreamId() {
        return streamId;
    }
    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
