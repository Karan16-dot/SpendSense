CREATE TABLE categories
(
    id UUID PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    icon VARCHAR(100) NOT NULL,

    color VARCHAR(20) NOT NULL,

    type VARCHAR(20) NOT NULL,

    is_default BOOLEAN NOT NULL DEFAULT FALSE,

    user_id UUID NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_category_user
        FOREIGN KEY (user_id)
            REFERENCES users(id),

    CONSTRAINT uk_category_user_name
        UNIQUE (user_id, name)
);