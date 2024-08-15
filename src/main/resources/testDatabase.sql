DROP TABLE IF EXISTS movies;

CREATE TABLE IF NOT EXISTS movies (
  id IDENTITY PRIMARY KEY,        
  title VARCHAR(255) NOT NULL,         
  description TEXT,          
  director VARCHAR(255),              
  country VARCHAR(255),                
  genre VARCHAR(100)                    
);

INSERT INTO movies (title, description, director, country, genre) VALUES
('Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.', 'Christopher Nolan', 'USA', 'Science Fiction'),
('Parasite', 'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.', 'Bong Joon Ho', 'South Korea', 'Thriller');
