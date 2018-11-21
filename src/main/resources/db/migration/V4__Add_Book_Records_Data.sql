# INSERT INTO `librarydb`.`user` (`id`, `username`, `password`, `name`, `active`)
# VALUES (2, 'pavel_e', '$2a$08$aeX/AxaUbIIuzYiSPXpbvuJNTdX4YTVgt3YIaGJG/SgVsNXWtDm6G', 'Pavel Epanechkin', true),
#        (3, 'mikhail', '$2a$08$aeX/AxaUbIIuzYiSPXpbvuJNTdX4YTVgt3YIaGJG/SgVsNXWtDm6G', 'Mikhail Kozlov', true),
#        (4, 'denis_k', '$2a$08$aeX/AxaUbIIuzYiSPXpbvuJNTdX4YTVgt3YIaGJG/SgVsNXWtDm6G', 'Kruminsh Denis', true);
#
# INSERT INTO `librarydb`.`userroles` (`user_id`, `roles`)
# VALUES (2, 'COMMON_USER'), (2, 'READER'),
#        (3, 'COMMON_USER'), (3, 'SUPPLIER'),
#        (4, 'COMMON_USER'), (4, 'READER');

INSERT INTO `librarydb`.`orderbooks` (`id`, `book_id`, `supplier_id`, `start_date`, `end_date`)
VALUES (1, 1, 3, '2018-05-01 08:44:07', '2018-05-03 08:03:01');

INSERT INTO `librarydb`.`librarybooks` (`id`, `book_id`, `reader_id`, `start_date`, `end_date`)
VALUES (1, 1, 2, '2018-05-20 15:38:00', '2018-05-30 05:59:00'),
       (2, 3, 4, NULL, NULL);

INSERT INTO `librarydb`.`exchangebooks` (`id`, `book_id`, `owner_id`, `open_exchange_date`, `reader_id`, `start_date`, `end_date`)
VALUES (1, 2, 4, '2018-05-15 17:02:50', 2, '2018-05-15 18:09:10', '2018-05-30 12:03:00'),
       (2, 2, 4, NULL, NULL, NULL, NULL);
