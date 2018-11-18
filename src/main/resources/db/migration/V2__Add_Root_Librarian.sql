INSERT INTO `librarydb`.`user` (`id`, `username`, `password`, `name`, `active`)
  VALUES (1, 'root', '$2a$08$qc/AglGk6aj.jH9HJJA9e.wE9NNVz8Zjy0g4VsvtpgJp35LcrD3Iy', 'Oleynik Maxim', true);

INSERT INTO `librarydb`.`userroles` (`user_id`, `roles`)
  VALUES (1, 'COMMON_USER'), (1, 'LIBRARIAN');
