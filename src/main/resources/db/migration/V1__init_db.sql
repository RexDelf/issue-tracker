CREATE TABLE IF NOT EXISTS "user"(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100),
    surname VARCHAR(100)
);
CREATE TABLE IF NOT EXISTS "sprint"(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    goals TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "priority"(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "status"(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "ticket"(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    reporter_id BIGINT NOT NULL,
    assignee_id BIGINT,
    status_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    priority_id BIGINT NOT NULL,
    FOREIGN KEY (reporter_id) REFERENCES "user" (id),
    FOREIGN KEY (assignee_id) REFERENCES "user" (id),
    FOREIGN KEY (status_id) REFERENCES "status" (id),
    FOREIGN KEY (priority_id) REFERENCES "priority" (id)
);

CREATE TABLE IF NOT EXISTS "sprint_ticket"(
    id BIGSERIAL PRIMARY KEY,
    sprint_id BIGINT NOT NULL,
    ticket_id BIGINT NOT NULL,
    FOREIGN KEY (sprint_id) REFERENCES "sprint" (id),
    FOREIGN KEY (ticket_id) REFERENCES "ticket" (id)
)