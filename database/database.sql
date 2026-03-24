-- ============================================
--   Pacman Game Database Setup Script
--   Database: MySQL
--   Schema: pacman
-- ============================================

CREATE DATABASE IF NOT EXISTS pacman;
USE pacman;

-- ============================================
--   TABLE: users
-- ============================================
CREATE TABLE IF NOT EXISTS users (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(50)  NOT NULL UNIQUE,
    password        VARCHAR(50)  NULL,               -- NULL for guest accounts
    totalGamesPlayed INT         NOT NULL DEFAULT 0,
    highScore       INT          NOT NULL DEFAULT 0,
    totalWins       INT          NOT NULL DEFAULT 0,
    totalLosses     INT          NOT NULL DEFAULT 0,
    levelReached    INT          NOT NULL DEFAULT 1
);

-- ============================================
--   TABLE: histories
-- ============================================
CREATE TABLE IF NOT EXISTS histories (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(50)  NOT NULL,
    totalScores     INT          NOT NULL DEFAULT 0,
    levelReached    INT          NOT NULL DEFAULT 1,
    result          VARCHAR(20)  NOT NULL,            -- 'Win', 'Lose', 'Surrender'
    gameDate        DATETIME     NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE
);

-- ============================================
--   FAKE DATA: users (10 records)
-- ============================================
INSERT INTO users (username, password, totalGamesPlayed, highScore, totalWins, totalLosses, levelReached) VALUES
('shadow99',   'pass1234',  15, 480, 6, 9,  5),
('neonbyte',   'qwer5678',  22, 610, 10, 12, 5),
('ghostkill',  'ghost123',  8,  290, 2, 6,  3),
('pacmaster',  'pac9999',   30, 750, 14, 16, 5),
('liraky',     'lira2024',  5,  150, 1, 4,  2),
('zephyr77',   'zephy777',  18, 530, 8, 10, 5),
('dotmuncher', 'dots4321',  12, 320, 3, 9,  4),
('voidwalker', 'void0000',  25, 690, 11, 14, 5),
('retrogame',  'retro111',  7,  210, 2, 5,  3),
('guestuser1',  NULL,        1,  40,  0, 1,  1);  -- guest account (no password)

-- ============================================
--   FAKE DATA: histories (10 records)
-- ============================================
INSERT INTO histories (username, totalScores, levelReached, result, gameDate) VALUES
('shadow99',    480, 5, 'Win',       '2025-03-01 14:22:10'),
('neonbyte',    610, 5, 'Win',       '2025-03-05 09:45:33'),
('ghostkill',   120, 2, 'Lose',      '2025-03-07 18:30:00'),
('pacmaster',   750, 5, 'Win',       '2025-03-10 20:15:47'),
('liraky',       80, 1, 'Surrender', '2025-03-12 11:05:22'),
('zephyr77',    530, 5, 'Win',       '2025-03-14 16:50:09'),
('dotmuncher',  200, 3, 'Lose',      '2025-03-15 22:10:55'),
('voidwalker',  690, 5, 'Win',       '2025-03-17 08:25:41'),
('retrogame',   170, 3, 'Surrender', '2025-03-18 13:40:18'),
('pacmaster',   310, 3, 'Lose',      '2025-03-20 19:55:30');