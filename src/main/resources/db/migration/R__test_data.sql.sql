-- Insert users
INSERT INTO "user" (username, password, name, surname) VALUES
                                                           ('john_doe', 'password1', 'John', 'Doe'),
                                                           ('jane_doe', 'password2', 'Jane', 'Doe'),
                                                           ('alice', 'password3', 'Alice', 'Smith'),
                                                           ('bob', 'password4', 'Bob', 'Brown');

-- Insert sprint data
INSERT INTO "sprint" (name, start_date, end_date, goals) VALUES
                                                             ('Sprint 1', '2023-07-01', '2023-07-14', 'Goal1, Goal2, Goal3'),
                                                             ('Sprint 2', '2023-07-15', '2023-07-28', 'Goal4, Goal5, Goal6');

-- Insert priority data
INSERT INTO "priority" (name) VALUES
                                  ('Low'),
                                  ('Medium'),
                                  ('High'),
                                  ('Critical');

-- Insert status data
INSERT INTO "status" (name) VALUES
                                ('Open'),
                                ('In Progress'),
                                ('Resolved'),
                                ('Closed'),
                                ('Reopened');

-- Insert ticket data
INSERT INTO "ticket" (title, description, reporter_id, assignee_id, status_id, created_at, priority_id) VALUES
                                                                                                            ('Bug 1', 'Description for Bug 1', 1, 2, 1, '2023-07-01 09:00:00', 3),
                                                                                                            ('Feature 1', 'Description for Feature 1', 3, 4, 2, '2023-07-01 10:00:00', 2),
                                                                                                            ('Bug 2', 'Description for Bug 2', 1, 3, 1, '2023-07-02 14:00:00', 4),
                                                                                                            ('Task 1', 'Description for Task 1', 2, 4, 3, '2023-07-01 11:00:00', 1);

-- Insert sprint_ticket data
INSERT INTO "sprint_ticket" (sprint_id, ticket_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (1, 3),
                                                       (1, 4);