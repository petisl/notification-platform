create TABLE message_media
(
    id           bigint       NOT NULL AUTO_INCREMENT,
    file_name    VARCHAR(200) NOT NULL,
    file_type    VARCHAR(120) NOT NULL,
    file_size    BIGINT       NOT NULL,
    date_created timestamp,
    PRIMARY KEY (id)
);

create TABLE mail_model
(
    id               bigint        NOT NULL AUTO_INCREMENT,
    subject          VARCHAR(250)  NOT NULL,
    sender           VARCHAR(200)  NOT NULL,
    recipient        TEXT          NOT NULL,
    bcc              TEXT          NULL,
    cc               TEXT          NULL,
    message          TEXT          NOT NULL,
    type             VARCHAR(150)  NULL,
    template_name    VARCHAR(150)  NULL,
    has_attachment   boolean       NULL,
    use_template     boolean       NULL,
    has_url_location boolean       NULL,
    message_map      varchar(5000) NULL,
    url              VARCHAR(1000) NULL,
    message_media    BIGINT        NULL,
    organization     BIGINT        NULL,
    date_created     timestamp,
    PRIMARY KEY (id),
    CONSTRAINT message_media
        FOREIGN KEY (message_media)
            REFERENCES message_media (id)
            ON delete CASCADE
            ON update CASCADE
);



create TABLE notify_model
(
    id           bigint       NOT NULL AUTO_INCREMENT,
    title        VARCHAR(150) NOT NULL,
    message      TEXT         NOT NULL,
    users        BIGINT       NOT NULL,
    organization BIGINT       NULL,
    is_read      boolean      NULL,
    date_created timestamp,
    PRIMARY KEY (id)
);


create TABLE email_setup
(
    id                bigint       NOT NULL AUTO_INCREMENT,
    username          varchar(50)  NOT NULL,
    password          varchar(200) DEFAULT NULL,
    host              varchar(150) NOT NULL,
    port              int          NOT NULL,
    protocol          varchar(10)  NOT NULL,
    smtp_auth         boolean      DEFAULT NULL,
    starttls_enabled  boolean      DEFAULT NULL,
    starttls_required boolean      DEFAULT NULL,
    primary_sender    varchar(150) NOT NULL,
    secondary_sender  varchar(150) DEFAULT NULL,
    status            bit(1)       DEFAULT NULL,
    enabled           boolean      DEFAULT NULL,
    organization      bigint       DEFAULT NULL,
    date_created      timestamp,
    PRIMARY KEY (id)
);
