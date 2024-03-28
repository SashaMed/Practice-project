CREATE TABLE Category (
    uuid UUID PRIMARY KEY,
    title TEXT NOT NULL
);

CREATE TABLE Recipe (
    uuid UUID PRIMARY KEY,
    dtCreate TIMESTAMP NOT NULL,
    dtUpdate TIMESTAMP,
    title TEXT NOT NULL,
    categoryId UUID NOT NULL,
    description TEXT,
    composition TEXT, 
    FOREIGN KEY (categoryId) REFERENCES Category(uuid)
);

CREATE TABLE AppUser(
    uuid UUID PRIMARY KEY,
    dtCreate TIMESTAMP NOT NULL,
    dtUpdate TIMESTAMP,
    mail TEXT NOT NULL UNIQUE,
    fio TEXT NOT NULL,
    role TEXT NOT NULL,
    status TEXT NOT NULL
);


CREATE TABLE FavoriteRecipe (
    uuid UUID PRIMARY KEY,
    userId UUID NOT NULL,
    recipeId UUID NOT NULL,
    FOREIGN KEY (userId) REFERENCES AppUser(uuid),
    FOREIGN KEY (recipeId) REFERENCES Recipe(uuid)
);


CREATE TABLE Product (
    uuid UUID PRIMARY KEY,
    dtCreate TIMESTAMP NOT NULL,
    dtUpdate TIMESTAMP,
    title TEXT NOT NULL,
    calories INT NOT NULL,
    proteins DOUBLE PRECISION NOT NULL,
    fats DOUBLE PRECISION NOT NULL,
    carbohydrates DOUBLE PRECISION NOT NULL
);

CREATE TABLE ProductModal (
    uuid UUID PRIMARY KEY,
    recipeId UUID NOT NULL,
    productId UUID NOT NULL,
    weight INT NOT NULL,
    FOREIGN KEY (recipeId) REFERENCES Recipe(uuid),
    FOREIGN KEY (productId) REFERENCES Product(uuid)
);

CREATE TABLE Review (
    uuid UUID PRIMARY KEY,
    text TEXT NOT NULL,
    recipeId UUID NOT NULL,
    parentReviewId UUID,
    userId UUID NOT NULL,
    likes INT NOT NULL,
    dtCreate TIMESTAMP NOT NULL,
    rating INT NOT NULL,
    FOREIGN KEY (recipeId) REFERENCES Recipe(uuid),
    FOREIGN KEY (parentReviewId) REFERENCES Review(uuid),
    FOREIGN KEY (userId) REFERENCES AppUser(uuid)
);