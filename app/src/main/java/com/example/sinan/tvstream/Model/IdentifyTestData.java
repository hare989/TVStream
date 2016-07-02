package com.example.sinan.tvstream.Model;

/**
 * Created by Sinan on 23.5.2016.
 */
public class IdentifyTestData {
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
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    private SubscriberId subscriber_id;

    public SubscriberId getSubscriberId() { return this.subscriber_id; }

    public void setSubscriberId(SubscriberId subscriber_id) { this.subscriber_id = subscriber_id; }
}
