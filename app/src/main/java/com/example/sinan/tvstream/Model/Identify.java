package com.example.sinan.tvstream.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

/**
 * Created by Sinan on 22.5.2016.
 */
public class Identify {

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

    public class SubscriberId{}

    public class AutoLogin
    {}


    public class Language
    {
        private String code;

        public String getCode() { return this.code; }

        public void setCode(String code) { this.code = code; }

        private String icon;

        public String getIcon() { return this.icon; }

        public void setIcon(String icon) { this.icon = icon; }
    }

    public class Languages
    {
        private Language language;

        public Language getLanguage() { return this.language; }

        public void setLanguage(Language language) { this.language = language; }
    }


    public class CaptionLanguage
    {
        private String code;

        public String getCode() { return this.code; }

        public void setCode(String code) { this.code = code; }

        private String icon;

        public String getIcon() { return this.icon; }

        public void setIcon(String icon) { this.icon = icon; }
    }

    public class CaptionLanguages
    {
        private CaptionLanguage caption_language;

        public CaptionLanguage getCaptionLanguage() { return this.caption_language; }

        public void setCaptionLanguage(CaptionLanguage caption_language) { this.caption_language = caption_language; }
    }

    public class AudioLanguage
    {
        private String code;

        public String getCode() { return this.code; }

        public void setCode(String code) { this.code = code; }

        private String icon;

        public String getIcon() { return this.icon; }

        public void setIcon(String icon) { this.icon = icon; }
    }

    public class AudioLanguages
    {
        private AudioLanguage audio_language;

        public AudioLanguage getAudioLanguage() { return this.audio_language; }

        public void setAudioLanguage(AudioLanguage audio_language) { this.audio_language = audio_language; }
    }

    public class AutoDiagnostic
    {}

    public class Endpoints
    {}

    public class Diagnostics
    {
        private AutoDiagnostic auto_diagnostic;

        public AutoDiagnostic getAutoDiagnostic() { return this.auto_diagnostic; }

        public void setAutoDiagnostic(AutoDiagnostic auto_diagnostic) { this.auto_diagnostic = auto_diagnostic; }

        private Endpoints endpoints;

        public Endpoints getEndpoints() { return this.endpoints; }

        public void setEndpoints(Endpoints endpoints) { this.endpoints = endpoints; }
    }

    public class Services
    {}


    public class FacebookConnectEnabled
    {}

    public class JwtConnectEnabled
    {}

    public class AnonymousUser
    {}

    public class Bitrate
    {
        private String value;

        public String getValue() { return this.value; }

        public void setValue(String value) { this.value = value; }

        private String bandwidth;

        public String getBandwidth() { return this.bandwidth; }

        public void setBandwidth(String bandwidth) { this.bandwidth = bandwidth; }

        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }
    }

    public class Bitrates
    {
        private ArrayList<Bitrate> bitrate;

        public ArrayList<Bitrate> getBitrate() { return this.bitrate; }

        public void setBitrate(ArrayList<Bitrate> bitrate) { this.bitrate = bitrate; }
    }

    public class Legal
    {}

    public class Info
    {}

    public class Help
    {}

    public class Contact
    {}


    public class Attributes1
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Transform
    {
        private Attributes1 attributes;

        public Attributes1 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes1 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources
    {
        private Transform transform;

        public Transform getTransform() { return this.transform; }

        public void setTransform(Transform transform) { this.transform = transform; }
    }

    public class Image
    {
        private Resources resources;

        public Resources getResources() { return this.resources; }

        public void setResources(Resources resources) { this.resources = resources; }
    }

    public class Attributes2
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List
    {
        private Attributes2 attributes;

        public Attributes2 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes2 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes3
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Home
    {
        private Attributes3 attributes;

        public Attributes3 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes3 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes4
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class All
    {
        private Attributes4 attributes;

        public Attributes4 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes4 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources2
    {
        private List list;

        public List getList() { return this.list; }

        public void setList(List list) { this.list = list; }

        private Home home;

        public Home getHome() { return this.home; }

        public void setHome(Home home) { this.home = home; }

        private All all;

        public All getAll() { return this.all; }

        public void setAll(All all) { this.all = all; }
    }

    public class Channel
    {
        private Resources2 resources;

        public Resources2 getResources() { return this.resources; }

        public void setResources(Resources2 resources) { this.resources = resources; }
    }

    public class Attributes5
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Genre
    {
        private Attributes5 attributes;

        public Attributes5 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes5 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes6
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class LastUpdated
    {
        private Attributes6 attributes;

        public Attributes6 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes6 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes7
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List2
    {
        private Attributes7 attributes;

        public Attributes7 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes7 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes8
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Recomended
    {
        private Attributes8 attributes;

        public Attributes8 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes8 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes9
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class ProgramCategory
    {
        private Attributes9 attributes;

        public Attributes9 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes9 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources3
    {
        private Genre genre;

        public Genre getGenre() { return this.genre; }

        public void setGenre(Genre genre) { this.genre = genre; }

        private LastUpdated last_updated;

        public LastUpdated getLastUpdated() { return this.last_updated; }

        public void setLastUpdated(LastUpdated last_updated) { this.last_updated = last_updated; }

        private List2 list;

        public List2 getList() { return this.list; }

        public void setList(List2 list) { this.list = list; }

        private Recomended recomended;

        public Recomended getRecomended() { return this.recomended; }

        public void setRecomended(Recomended recomended) { this.recomended = recomended; }

        private ProgramCategory program_category;

        public ProgramCategory getProgramCategory() { return this.program_category; }

        public void setProgramCategory(ProgramCategory program_category) { this.program_category = program_category; }
    }

    public class Epg
    {
        private Resources3 resources;

        public Resources3 getResources() { return this.resources; }

        public void setResources(Resources3 resources) { this.resources = resources; }
    }

    public class Attributes10
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class ReportException
    {
        private Attributes10 attributes;

        public Attributes10 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes10 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources4
    {
        private ReportException report_exception;

        public ReportException getReportException() { return this.report_exception; }

        public void setReportException(ReportException report_exception) { this.report_exception = report_exception; }
    }

    public class Error1
    {
        private Resources4 resources;

        public Resources4 getResources() { return this.resources; }

        public void setResources(Resources4 resources) { this.resources = resources; }
    }

    public class Attributes11
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Create
    {
        private Attributes11 attributes;

        public Attributes11 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes11 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes12
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Delete
    {
        private Attributes12 attributes;

        public Attributes12 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes12 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes13
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List3
    {
        private Attributes13 attributes;

        public Attributes13 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes13 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources5
    {
        private Create create;

        public Create getCreate() { return this.create; }

        public void setCreate(Create create) { this.create = create; }

        private Delete delete;

        public Delete getDelete() { return this.delete; }

        public void setDelete(Delete delete) { this.delete = delete; }

        private List3 list;

        public List3 getList() { return this.list; }

        public void setList(List3 list) { this.list = list; }
    }

    public class FavoriteChannel
    {
        private Resources5 resources;

        public Resources5 getResources() { return this.resources; }

        public void setResources(Resources5 resources) { this.resources = resources; }
    }

    public class Attributes14
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Licence
    {
        private Attributes14 attributes;

        public Attributes14 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes14 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources6
    {
        private Licence licence;

        public Licence getLicence() { return this.licence; }

        public void setLicence(Licence licence) { this.licence = licence; }
    }

    public class Playready
    {
        private Resources6 resources;

        public Resources6 getResources() { return this.resources; }

        public void setResources(Resources6 resources) { this.resources = resources; }
    }

    public class Attributes15
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class ContentBuyOptions
    {
        private Attributes15 attributes;

        public Attributes15 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes15 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes16
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Create2
    {
        private Attributes16 attributes;

        public Attributes16 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes16 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes17
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List4
    {
        private Attributes17 attributes;

        public Attributes17 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes17 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes18
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class TransactionLog
    {
        private Attributes18 attributes;

        public Attributes18 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes18 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources7
    {
        private ContentBuyOptions content_buy_options;

        public ContentBuyOptions getContentBuyOptions() { return this.content_buy_options; }

        public void setContentBuyOptions(ContentBuyOptions content_buy_options) { this.content_buy_options = content_buy_options; }

        private Create2 create;

        public Create2 getCreate() { return this.create; }

        public void setCreate(Create2 create) { this.create = create; }

        private List4 list;

        public List4 getList() { return this.list; }

        public void setList(List4 list) { this.list = list; }

        private TransactionLog transaction_log;

        public TransactionLog getTransactionLog() { return this.transaction_log; }

        public void setTransactionLog(TransactionLog transaction_log) { this.transaction_log = transaction_log; }
    }

    public class Purchase
    {
        private Resources7 resources;

        public Resources7 getResources() { return this.resources; }

        public void setResources(Resources7 resources) { this.resources = resources; }
    }

    public class Attributes19
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Create3
    {
        private Attributes19 attributes;

        public Attributes19 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes19 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes20
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Delete2
    {
        private Attributes20 attributes;

        public Attributes20 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes20 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes21
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List5
    {
        private Attributes21 attributes;

        public Attributes21 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes21 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources8
    {
        private Create3 create;

        public Create3 getCreate() { return this.create; }

        public void setCreate(Create3 create) { this.create = create; }

        private Delete2 delete;

        public Delete2 getDelete() { return this.delete; }

        public void setDelete(Delete2 delete) { this.delete = delete; }

        private List5 list;

        public List5 getList() { return this.list; }

        public void setList(List5 list) { this.list = list; }
    }

    public class Reminder
    {
        private Resources8 resources;

        public Resources8 getResources() { return this.resources; }

        public void setResources(Resources8 resources) { this.resources = resources; }
    }

    public class Attributes22
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List6
    {
        private Attributes22 attributes;

        public Attributes22 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes22 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources9
    {
        private List6 list;

        public List6 getList() { return this.list; }

        public void setList(List6 list) { this.list = list; }
    }

    public class Catchup
    {
        private Resources9 resources;

        public Resources9 getResources() { return this.resources; }

        public void setResources(Resources9 resources) { this.resources = resources; }
    }

    public class Attributes23
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Login
    {
        private Attributes23 attributes;

        public Attributes23 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes23 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes24
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Logout
    {
        private Attributes24 attributes;

        public Attributes24 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes24 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes25
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class PasswordRecovery
    {
        private Attributes25 attributes;

        public Attributes25 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes25 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes26
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Profile
    {
        private Attributes26 attributes;

        public Attributes26 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes26 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes27
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Update
    {
        private Attributes27 attributes;

        public Attributes27 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes27 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes28
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Delete3
    {
        private Attributes28 attributes;

        public Attributes28 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes28 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes29
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class ResendVerificationEmail
    {
        private Attributes29 attributes;

        public Attributes29 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes29 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources10
    {
        private Login login;

        public Login getLogin() { return this.login; }

        public void setLogin(Login login) { this.login = login; }

        private Logout logout;

        public Logout getLogout() { return this.logout; }

        public void setLogout(Logout logout) { this.logout = logout; }

        private PasswordRecovery password_recovery;

        public PasswordRecovery getPasswordRecovery() { return this.password_recovery; }

        public void setPasswordRecovery(PasswordRecovery password_recovery) { this.password_recovery = password_recovery; }

        private Profile profile;

        public Profile getProfile() { return this.profile; }

        public void setProfile(Profile profile) { this.profile = profile; }

        private Update update;

        public Update getUpdate() { return this.update; }

        public void setUpdate(Update update) { this.update = update; }

        private Delete3 delete;

        public Delete3 getDelete() { return this.delete; }

        public void setDelete(Delete3 delete) { this.delete = delete; }

        private ResendVerificationEmail resend_verification_email;

        public ResendVerificationEmail getResendVerificationEmail() { return this.resend_verification_email; }

        public void setResendVerificationEmail(ResendVerificationEmail resend_verification_email) { this.resend_verification_email = resend_verification_email; }
    }

    public class User
    {
        private Resources10 resources;

        public Resources10 getResources() { return this.resources; }

        public void setResources(Resources10 resources) { this.resources = resources; }
    }

    public class Attributes30
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Channel2
    {
        private Attributes30 attributes;

        public Attributes30 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes30 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes31
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List7
    {
        private Attributes31 attributes;

        public Attributes31 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes31 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources11
    {
        private Channel2 channel;

        public Channel2 getChannel() { return this.channel; }

        public void setChannel(Channel2 channel) { this.channel = channel; }

        private List7 list;

        public List7 getList() { return this.list; }

        public void setList(List7 list) { this.list = list; }
    }

    public class Vod
    {
        private Resources11 resources;

        public Resources11 getResources() { return this.resources; }

        public void setResources(Resources11 resources) { this.resources = resources; }
    }

    public class Attributes32
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Structure
    {
        private Attributes32 attributes;

        public Attributes32 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes32 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes33
    {
        private String refresh_interval;

        public String getRefreshInterval() { return this.refresh_interval; }

        public void setRefreshInterval(String refresh_interval) { this.refresh_interval = refresh_interval; }

        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Search
    {
        private Attributes33 attributes;

        public Attributes33 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes33 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources12
    {
        private Structure structure;

        public Structure getStructure() { return this.structure; }

        public void setStructure(Structure structure) { this.structure = structure; }

        private Search search;

        public Search getSearch() { return this.search; }

        public void setSearch(Search search) { this.search = search; }
    }

    public class VodCatalog
    {
        private Resources12 resources;

        public Resources12 getResources() { return this.resources; }

        public void setResources(Resources12 resources) { this.resources = resources; }
    }

    public class Attributes34
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Create4
    {
        private Attributes34 attributes;

        public Attributes34 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes34 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes35
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List8
    {
        private Attributes35 attributes;

        public Attributes35 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes35 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources13
    {
        private Create4 create;

        public Create4 getCreate() { return this.create; }

        public void setCreate(Create4 create) { this.create = create; }

        private List8 list;

        public List8 getList() { return this.list; }

        public void setList(List8 list) { this.list = list; }
    }

    public class VodRating
    {
        private Resources13 resources;

        public Resources13 getResources() { return this.resources; }

        public void setResources(Resources13 resources) { this.resources = resources; }
    }

    public class Attributes36
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class Delete4
    {
        private Attributes36 attributes;

        public Attributes36 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes36 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Attributes37
    {
        private String use_session;

        public String getUseSession() { return this.use_session; }

        public void setUseSession(String use_session) { this.use_session = use_session; }
    }

    public class List9
    {
        private Attributes37 attributes;

        public Attributes37 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes37 attributes) { this.attributes = attributes; }

        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Resources14
    {
        private Delete4 delete;

        public Delete4 getDelete() { return this.delete; }

        public void setDelete(Delete4 delete) { this.delete = delete; }

        private List9 list;

        public List9 getList() { return this.list; }

        public void setList(List9 list) { this.list = list; }
    }

    public class ApplicationInstallation
    {
        private Resources14 resources;

        public Resources14 getResources() { return this.resources; }

        public void setResources(Resources14 resources) { this.resources = resources; }
    }

    public class Modules
    {
        private Image image;

        public Image getImage() { return this.image; }

        public void setImage(Image image) { this.image = image; }

        private Channel channel;

        public Channel getChannel() { return this.channel; }

        public void setChannel(Channel channel) { this.channel = channel; }

        private Epg epg;

        public Epg getEpg() { return this.epg; }

        public void setEpg(Epg epg) { this.epg = epg; }

        private Error1 error1;

        public Error1 getError1() { return this.error1; }

        public void setError1(Error1 error) { this.error1 = error; }

        private FavoriteChannel favorite_channel;

        public FavoriteChannel getFavoriteChannel() { return this.favorite_channel; }

        public void setFavoriteChannel(FavoriteChannel favorite_channel) { this.favorite_channel = favorite_channel; }

        private Playready playready;

        public Playready getPlayready() { return this.playready; }

        public void setPlayready(Playready playready) { this.playready = playready; }

        private Purchase purchase;

        public Purchase getPurchase() { return this.purchase; }

        public void setPurchase(Purchase purchase) { this.purchase = purchase; }

        private Reminder reminder;

        public Reminder getReminder() { return this.reminder; }

        public void setReminder(Reminder reminder) { this.reminder = reminder; }

        private Catchup catchup;

        public Catchup getCatchup() { return this.catchup; }

        public void setCatchup(Catchup catchup) { this.catchup = catchup; }

        private User user;

        public User getUser() { return this.user; }

        public void setUser(User user) { this.user = user; }

        private Vod vod;

        public Vod getVod() { return this.vod; }

        public void setVod(Vod vod) { this.vod = vod; }

        private VodCatalog vod_catalog;

        public VodCatalog getVodCatalog() { return this.vod_catalog; }

        public void setVodCatalog(VodCatalog vod_catalog) { this.vod_catalog = vod_catalog; }

        private VodRating vod_rating;

        public VodRating getVodRating() { return this.vod_rating; }

        public void setVodRating(VodRating vod_rating) { this.vod_rating = vod_rating; }

        private ApplicationInstallation application_installation;

        public ApplicationInstallation getApplicationInstallation() { return this.application_installation; }

        public void setApplicationInstallation(ApplicationInstallation application_installation) { this.application_installation = application_installation; }
    }

    public class Vsc
    {
        private String uri;

        public String getUri() { return this.uri; }

        public void setUri(String uri) { this.uri = uri; }
    }

    public class Variables
    {
        private Vsc vsc;

        public Vsc getVsc() { return this.vsc; }

        public void setVsc(Vsc vsc) { this.vsc = vsc; }
    }

    private Modules modules;

    public Modules getModules() { return this.modules; }

    public void setModules(Modules modules) { this.modules = modules; }

    private Variables variables;

    public Variables getVariables() { return this.variables; }

    public void setVariables(Variables variables) { this.variables = variables; }

    private String application_branding;

    public String getApplicationBranding() { return this.application_branding; }

    public void setApplicationBranding(String application_branding) { this.application_branding = application_branding; }

    private String language_source;

    public String getLanguageSource() { return this.language_source; }

    public void setLanguageSource(String language_source) { this.language_source = language_source; }

    private String currency;

    public String getCurrency() { return this.currency; }

    public void setCurrency(String currency) { this.currency = currency; }

    private String facebook_connect_enabled;

    public String getFacebookConnectEnabled() { return this.facebook_connect_enabled; }

    public void setFacebookConnectEnabled(String facebook_connect_enabled) { this.facebook_connect_enabled = facebook_connect_enabled; }

    private Object jwt_connect_enabled;

    public Object getJwtConnectEnabled() { return this.jwt_connect_enabled; }

    public void setJwtConnectEnabled(Object jwt_connect_enabled) { this.jwt_connect_enabled = jwt_connect_enabled; }

    private AnonymousUser anonymous_user;

    public AnonymousUser getAnonymousUser() { return this.anonymous_user; }

    public void setAnonymousUser(AnonymousUser anonymous_user) { this.anonymous_user = anonymous_user; }

    private Bitrates bitrates;

    public Bitrates getBitrates() { return this.bitrates; }

    public void setBitrates(Bitrates bitrates) { this.bitrates = bitrates; }

    private String application_new_version;

    public String getApplicationNewVersion() { return this.application_new_version; }

    public void setApplicationNewVersion(String application_new_version) { this.application_new_version = application_new_version; }

    private String application_force_update;

    public String getApplicationForceUpdate() { return this.application_force_update; }

    public void setApplicationForceUpdate(String application_force_update) { this.application_force_update = application_force_update; }

    private String application_update_url;

    public String getApplicationUpdateUrl() { return this.application_update_url; }

    public void setApplicationUpdateUrl(String application_update_url) { this.application_update_url = application_update_url; }

    private Legal legal;

    public Legal getLegal() { return this.legal; }

    public void setLegal(Legal legal) { this.legal = legal; }

    private Info info;

    public Info getInfo() { return this.info; }

    public void setInfo(Info info) { this.info = info; }

    private Help help;

    public Help getHelp() { return this.help; }

    public void setHelp(Help help) { this.help = help; }

    private Contact contact;

    public Contact getContact() { return this.contact; }

    public void setContact(Contact contact) { this.contact = contact; }

    private String facebook_app_id;

    public String getFacebookAppId() { return this.facebook_app_id; }

    public void setFacebookAppId(String facebook_app_id) { this.facebook_app_id = facebook_app_id; }

    private AudioLanguages audio_languages;

    public AudioLanguages getAudioLanguages() { return this.audio_languages; }

    public void setAudioLanguages(AudioLanguages audio_languages) { this.audio_languages = audio_languages; }

    private Diagnostics diagnostics;

    public Diagnostics getDiagnostics() { return this.diagnostics; }

    public void setDiagnostics(Diagnostics diagnostics) { this.diagnostics = diagnostics; }

    private Services services;

    public Services getServices() { return this.services; }

    public void setServices(Services services) { this.services = services; }

    private CaptionLanguages caption_languages;

    public CaptionLanguages getCaptionLanguages() { return this.caption_languages; }

    public void setCaptionLanguages(CaptionLanguages caption_languages) { this.caption_languages = caption_languages; }

    private Languages languages;

    public Languages getLanguages() { return this.languages; }

    public void setLanguages(Languages languages) { this.languages = languages; }

    private Error error;

        public Error getError() { return this.error; }

        public void setError(Error error) { this.error = error; }

        private String error_reporting_level;

        public String getErrorReportingLevel() { return this.error_reporting_level; }

        public void setErrorReportingLevel(String error_reporting_level) { this.error_reporting_level = error_reporting_level; }

        private String session_id;

        public String getSessionId() { return this.session_id; }

        public void setSessionId(String session_id) { this.session_id = session_id; }

        private String application_id;

        public String getApplicationId() { return this.application_id; }

        public void setApplicationId(String application_id) { this.application_id = application_id; }

        private String login_required;

        public String getLoginRequired() { return this.login_required; }

        public void setLoginRequired(String login_required) { this.login_required = login_required; }

        private AutoLogin auto_login;

        public AutoLogin getAutoLogin() { return this.auto_login; }

        public void setAutoLogin(AutoLogin auto_login) { this.auto_login = auto_login; }

        private String hide_login_subscriber_id;

        public String getHideLoginSubscriberId() { return this.hide_login_subscriber_id; }

        public void setHideLoginSubscriberId(String hide_login_subscriber_id) { this.hide_login_subscriber_id = hide_login_subscriber_id; }

        private Object subscriber_id;

        public Object getSubscriberId() { return this.subscriber_id; }

        public void setSubscriberId(Object subscriber_id) { this.subscriber_id = subscriber_id; }

        private SubscriberId subscriber_id1;

        public SubscriberId getSubscriber_id1() {
            return subscriber_id1;
        }

        public void setSubscriber_id1(SubscriberId subscriber_id1) {
            this.subscriber_id1 = subscriber_id1;
        }

        private String allow_any_subscriber_login;

        public String getAllowAnySubscriberLogin() { return this.allow_any_subscriber_login; }

        public void setAllowAnySubscriberLogin(String allow_any_subscriber_login) { this.allow_any_subscriber_login = allow_any_subscriber_login; }

        private String epg_icon_base_url;

        public String getEpgIconBaseUrl() { return this.epg_icon_base_url; }

        public void setEpgIconBaseUrl(String epg_icon_base_url) { this.epg_icon_base_url = epg_icon_base_url; }

        private String genre_icon_base_url;

        public String getGenreIconBaseUrl() { return this.genre_icon_base_url; }

        public void setGenreIconBaseUrl(String genre_icon_base_url) { this.genre_icon_base_url = genre_icon_base_url; }

        private String epg_program_category_icon_base_url;

        public String getEpgProgramCategoryIconBaseUrl() { return this.epg_program_category_icon_base_url; }

        public void setEpgProgramCategoryIconBaseUrl(String epg_program_category_icon_base_url) { this.epg_program_category_icon_base_url = epg_program_category_icon_base_url; }

        private String epg_sqlite_url;

        public String getEpgSqliteUrl() { return this.epg_sqlite_url; }

        public void setEpgSqliteUrl(String epg_sqlite_url) { this.epg_sqlite_url = epg_sqlite_url; }

        private String epg_dump_xml_url;

        public String getEpgDumpXmlUrl() { return this.epg_dump_xml_url; }

        public void setEpgDumpXmlUrl(String epg_dump_xml_url) { this.epg_dump_xml_url = epg_dump_xml_url; }

        private String epg_dump_json_url;

        public String getEpgDumpJsonUrl() { return this.epg_dump_json_url; }

        public void setEpgDumpJsonUrl(String epg_dump_json_url) { this.epg_dump_json_url = epg_dump_json_url; }

        private String epg_icon_template_url;

        public String getEpgIconTemplateUrl() { return this.epg_icon_template_url; }

        public void setEpgIconTemplateUrl(String epg_icon_template_url) { this.epg_icon_template_url = epg_icon_template_url; }

        private String rating_icon_template_url;

        public String getRatingIconTemplateUrl() { return this.rating_icon_template_url; }

        public void setRatingIconTemplateUrl(String rating_icon_template_url) { this.rating_icon_template_url = rating_icon_template_url; }

        private String logo;

        public String getLogo() { return this.logo; }

        public void setLogo(String logo) { this.logo = logo; }

        private String logo_small;

        public String getLogoSmall() { return this.logo_small; }

        public void setLogoSmall(String logo_small) { this.logo_small = logo_small; }

        private String lock_thumbnail_url;

        public String getLockThumbnailUrl() { return this.lock_thumbnail_url; }

        public void setLockThumbnailUrl(String lock_thumbnail_url) { this.lock_thumbnail_url = lock_thumbnail_url; }

        private String default_language;

        public String getDefaultLanguage() { return this.default_language; }

        public void setDefaultLanguage(String default_language) { this.default_language = default_language; }

}
