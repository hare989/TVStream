package com.example.sinan.tvstream.Model;

/**
 * Created by Sinan on 23.5.2016.
 */
public class UserLogin {
    public class Attributes
    {
        private String status;

        public String getStatus() { return this.status; }

        public void setStatus(String status) { this.status = status; }
    }

    public class Error
    {
        private Attributes attributes;

        public Attributes getAttributes() { return this.attributes; }

        public void setAttributes(Attributes attributes) { this.attributes = attributes; }
    }

    public class ActiveFilters
    {}

    public class ContentAudience
    {
        private String adult;

        public String getAdult() { return this.adult; }

        public void setAdult(String adult) { this.adult = adult; }
    }

    public class PurchasePinCode
    {}

    public class Uid
    {}

    public class Services
    {
        private String advertisement;

        public String getAdvertisement() { return this.advertisement; }

        public void setAdvertisement(String advertisement) { this.advertisement = advertisement; }

        private String catchup_tv;

        public String getCatchupTv() { return this.catchup_tv; }

        public void setCatchupTv(String catchup_tv) { this.catchup_tv = catchup_tv; }

        private String client_api;

        public String getClientApi() { return this.client_api; }

        public void setClientApi(String client_api) { this.client_api = client_api; }

        private String cms;

        public String getCms() { return this.cms; }

        public void setCms(String cms) { this.cms = cms; }

        private String drm;

        public String getDrm() { return this.drm; }

        public void setDrm(String drm) { this.drm = drm; }

        private String epg;

        public String getEpg() { return this.epg; }

        public void setEpg(String epg) { this.epg = epg; }

        private String geo_lock;

        public String getGeoLock() { return this.geo_lock; }

        public void setGeoLock(String geo_lock) { this.geo_lock = geo_lock; }

        private String linear_channel;

        public String getLinearChannel() { return this.linear_channel; }

        public void setLinearChannel(String linear_channel) { this.linear_channel = linear_channel; }

        private String localization;

        public String getLocalization() { return this.localization; }

        public void setLocalization(String localization) { this.localization = localization; }

        private String parental_control;

        public String getParentalControl() { return this.parental_control; }

        public void setParentalControl(String parental_control) { this.parental_control = parental_control; }

        private String playlist;

        public String getPlaylist() { return this.playlist; }

        public void setPlaylist(String playlist) { this.playlist = playlist; }

        private String push_notifications;

        public String getPushNotifications() { return this.push_notifications; }

        public void setPushNotifications(String push_notifications) { this.push_notifications = push_notifications; }

        private String secure_streaming;

        public String getSecureStreaming() { return this.secure_streaming; }

        public void setSecureStreaming(String secure_streaming) { this.secure_streaming = secure_streaming; }

        private String self_care_portal;

        public String getSelfCarePortal() { return this.self_care_portal; }

        public void setSelfCarePortal(String self_care_portal) { this.self_care_portal = self_care_portal; }

        private String statistics;

        public String getStatistics() { return this.statistics; }

        public void setStatistics(String statistics) { this.statistics = statistics; }

        private String subscribers;

        public String getSubscribers() { return this.subscribers; }

        public void setSubscribers(String subscribers) { this.subscribers = subscribers; }

        private String vod;

        public String getVod() { return this.vod; }

        public void setVod(String vod) { this.vod = vod; }

        private String watch_folder;

        public String getWatchFolder() { return this.watch_folder; }

        public void setWatchFolder(String watch_folder) { this.watch_folder = watch_folder; }

        private String webcast;

        public String getWebcast() { return this.webcast; }

        public void setWebcast(String webcast) { this.webcast = webcast; }

        private String youtube_sync;

        public String getYoutubeSync() { return this.youtube_sync; }

        public void setYoutubeSync(String youtube_sync) { this.youtube_sync = youtube_sync; }

        private String vod_catalog;

        public String getVodCatalog() { return this.vod_catalog; }

        public void setVodCatalog(String vod_catalog) { this.vod_catalog = vod_catalog; }
    }


        private Error error;

        public Error getError() { return this.error; }

        public void setError(Error error) { this.error = error; }

        private String application_branding;

        public String getApplicationBranding() { return this.application_branding; }

        public void setApplicationBranding(String application_branding) { this.application_branding = application_branding; }

        private String id;

        public String getId() { return this.id; }

        public void setId(String id) { this.id = id; }

        private String first_name;

        public String getFirstName() { return this.first_name; }

        public void setFirstName(String first_name) { this.first_name = first_name; }

        private String last_name;

        public String getLastName() { return this.last_name; }

        public void setLastName(String last_name) { this.last_name = last_name; }

        private String pin_code;

        public String getPinCode() { return this.pin_code; }

        public void setPinCode(String pin_code) { this.pin_code = pin_code; }

        private String subscriber_id;

        public String getSubscriberId() { return this.subscriber_id; }

        public void setSubscriberId(String subscriber_id) { this.subscriber_id = subscriber_id; }

        private String parental_control_mode;

        public String getParentalControlMode() { return this.parental_control_mode; }

        public void setParentalControlMode(String parental_control_mode) { this.parental_control_mode = parental_control_mode; }

        private String rating_type;

        public String getRatingType() { return this.rating_type; }

        public void setRatingType(String rating_type) { this.rating_type = rating_type; }

        private String rating;

        public String getRating() { return this.rating; }

        public void setRating(String rating) { this.rating = rating; }

        private String push_notifications;

        public String getPushNotifications() { return this.push_notifications; }

        public void setPushNotifications(String push_notifications) { this.push_notifications = push_notifications; }

        private Object audio_language;

        public Object getAudioLanguage() { return this.audio_language; }

        public void setAudioLanguage(Object audio_language) { this.audio_language = audio_language; }

        private String email;

        public String getEmail() { return this.email; }

        public void setEmail(String email) { this.email = email; }

        private String use_mobile_networks;

        public String getUseMobileNetworks() { return this.use_mobile_networks; }

        public void setUseMobileNetworks(String use_mobile_networks) { this.use_mobile_networks = use_mobile_networks; }

        private String bitrate;

        public String getBitrate() { return this.bitrate; }

        public void setBitrate(String bitrate) { this.bitrate = bitrate; }

        private String application_language;

        public String getApplicationLanguage() { return this.application_language; }

        public void setApplicationLanguage(String application_language) { this.application_language = application_language; }

        private Object active_filters;

        public Object getActiveFilters() { return this.active_filters; }

        public void setActiveFilters(Object active_filters) { this.active_filters = active_filters; }

        private String created_at;

        public String getCreatedAt() { return this.created_at; }

        public void setCreatedAt(String created_at) { this.created_at = created_at; }

        private String updated_at;

        public String getUpdatedAt() { return this.updated_at; }

        public void setUpdatedAt(String updated_at) { this.updated_at = updated_at; }

        private String picture_format;

        public String getPictureFormat() { return this.picture_format; }

        public void setPictureFormat(String picture_format) { this.picture_format = picture_format; }

        private ContentAudience content_audience;

        public ContentAudience getContentAudience() { return this.content_audience; }

        public void setContentAudience(ContentAudience content_audience) { this.content_audience = content_audience; }

        private String adult_enable;

        public String getAdultEnable() { return this.adult_enable; }

        public void setAdultEnable(String adult_enable) { this.adult_enable = adult_enable; }

        private String external_id;

        public String getExternalId() { return this.external_id; }

        public void setExternalId(String external_id) { this.external_id = external_id; }

        private PurchasePinCode purchase_pin_code;

        public PurchasePinCode getPurchasePinCode() { return this.purchase_pin_code; }

        public void setPurchasePinCode(PurchasePinCode purchase_pin_code) { this.purchase_pin_code = purchase_pin_code; }

        private Object caption_language;

        public Object getCaptionLanguage() { return this.caption_language; }

        public void setCaptionLanguage(Object caption_language) { this.caption_language = caption_language; }

        private Object subtitle_language;

        public Object getSubtitleLanguage() { return this.subtitle_language; }

        public void setSubtitleLanguage(Object subtitle_language) { this.subtitle_language = subtitle_language; }

        private Uid uid;

        public Uid getUid() { return this.uid; }

        public void setUid(Uid uid) { this.uid = uid; }

        private String session_token;

        public String getSessionToken() { return this.session_token; }

        public void setSessionToken(String session_token) { this.session_token = session_token; }

        private String secure_streaming_token;

        public String getSecureStreamingToken() { return this.secure_streaming_token; }

        public void setSecureStreamingToken(String secure_streaming_token) { this.secure_streaming_token = secure_streaming_token; }

        private Services services;

        public Services getServices() { return this.services; }

        public void setServices(Services services) { this.services = services; }

}
