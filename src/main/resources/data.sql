INSERT INTO users (id, active, email, first_name, last_name, password, username)
VALUES (1, 1, 'admin@example.com', 'Admin', 'Adminov',
        '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', 'AdminaBe'),
       (2, 1, 'user@example.com', 'User', 'Userov',
        '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', 'UseraBe');


INSERT INTO roles (`id`, `role`)
VALUES (1, 'ADMINISTRATOR'),
       (2, 'USER');

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES (1, 1),
       (1, 2),
       (2, 2);

insert into book_authors (id, first_name, last_name)
values (1, 'Стефан', 'Цанев'),
       (2, 'Людмила', 'Филипова'),
       (3, 'Захари', 'Стоянов');


insert into books (id, genre, image_url, price, title, uuid, author_id)
values (1, 'ИСТОРИЯ',
        'https://covers.storytel.com/jpg-640/9789178659623.05c6ffc1-ec31-42b3-a222-f13a156a5b61?quality=70',
        20, 'Български Хроники. Том 1. Книга първа', '53006e77-1dd2-4d96-ad84-f6a71ccc3d92', 1),
       (2, 'РОМАН',
        'https://www.ciela.com/media/catalog/product/e/n/enthusiast_voynata-na-bukvite_otkas-01.jpg',
        22, 'Войната на Буквите', '3ed5a989-62d0-4347-9b78-9f5af6b95287', 2),
       (3, 'ИСТОРИЯ',
        'https://www.book.store.bg/lrgimg/42100/zapiski-po-bylgarskite-vystania.jpg',
        7.50, 'Записки по българските въстания', '3b07d6b7-c97e-4f3b-99d7-a8332f79d3ae', 3);



insert into articles (id, content, image_url, published, title, uuid, author_id)
values (1,
        'Никулден е един от важните православни празници. Посветен е на свети Николай Чудотворец, който се почита като избавител на пленниците и покровител на моряците, пътешествениците, търговците и банкерите. Празникът се отбелязва на 6 декември.',
        'https://stmost.info/images/NIKULDEN11.jpg', '2023-12-04', 'Никулден', '3ed5a989-62d0-2323-9b78-9f5af6b95287', 2),
       (2,
        'Ден на светите братя Кирил и Методий, на българската азбука, просвета и култура и на славянската книжовност е български официален празник, честван на 24 май. На този ден в България се чества делото на славянските просветители Кирил и Методий и българската просвета и култура, включително и създаването на кирилицата в Преславската книжовна школа.',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Cyril_Methodius25K.jpg/250px-Cyril_Methodius25K.jpg',
        '2023-12-04', 'Св. св. Кирил и Методи','3b07d6b7-1dd2-4f3b-99d7-a8332f79d3ae' , 1);
