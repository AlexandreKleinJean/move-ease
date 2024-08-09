DROP TABLE IF EXISTS "movies";

CREATE TABLE IF NOT EXISTS "movies" (
  "id" SERIAL PRIMARY KEY,        
  "title" TEXT NOT NULL,         
  "description" TEXT,          
  "director" TEXT,              
  "country" TEXT,                
  "genre" TEXT                    
);

INSERT INTO "movies" ("title", "description", "director", "country", "genre") VALUES
('Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.', 'Christopher Nolan', 'USA', 'Science Fiction'),
('Parasite', 'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.', 'Bong Joon Ho', 'South Korea', 'Thriller'),
('The Matrix', 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.', 'Lana Wachowski, Lilly Wachowski', 'USA', 'Action'),
('Amélie', 'Amélie is an innocent and naive girl in Paris with her own sense of justice.', 'Jean-Pierre Jeunet', 'France', 'Romantic Comedy'),
('Spirited Away', 'During her family''s move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches, and spirits, and where humans are changed into beasts.', 'Hayao Miyazaki', 'Japan', 'Animation');

SELECT * FROM "movies";
