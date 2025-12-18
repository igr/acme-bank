create table if not exists bank (
    bank_id uuid not null,
    name text not null,
    address text not null,
    primary key (bank_id)
);

create table if not exists customer (
    customer_id uuid not null,
    name text not null,
    primary key (customer_id)
);

create table if not exists account (
    account_id uuid not null,
    bank_id uuid not null,
    routing_number text not null,
    amount bigint not null,
    withdrawal_limit bigint not null,
    last_change timestamp not null,
    primary key (account_id),
    foreign key (bank_id) references bank(bank_id)
);

create table if not exists customer_account (
    customer_id uuid not null,
    account_id uuid not null,
    primary key (customer_id, account_id),
    foreign key (customer_id) references customer(customer_id),
    foreign key (account_id) references account(account_id)
);
insert into bank (bank_id, name, address) values ('5e3b38dc-7c1c-410e-ac70-8bdebddd0736', 'Acme Bank', '123 Main St');

insert into customer (customer_id, name) values ('e515b817-76de-4a73-9175-0fbf87f55342', 'Roselia Moore');
insert into customer (customer_id, name) values ('8022026c-df8e-4dda-a335-59393b651204', 'Foster Weimann');
insert into customer (customer_id, name) values ('ee711a4c-c39d-4eec-a919-033aea123692', 'Calvin Gulgowski');
insert into customer (customer_id, name) values ('b506d423-a00b-4e29-a7e3-c5733a29c84f', 'Milo Wiza');
insert into customer (customer_id, name) values ('c5016177-ae88-4311-a5a2-251207b9002c', 'Petra Bradtke DO');
insert into customer (customer_id, name) values ('39c81135-2bcd-43dc-977d-be96f2b3f5ff', 'Dion Braun PhD');
insert into customer (customer_id, name) values ('ff702e62-757e-4f11-a9ab-94d7391dfc2e', 'Dierdre Sauer');
insert into customer (customer_id, name) values ('88558e77-2b0a-49c1-a2bd-572a26e3628d', 'Glady Barton');
insert into customer (customer_id, name) values ('da1e8466-5767-4941-95b9-b22b21601f77', 'Johnathan Rodriguez');
insert into customer (customer_id, name) values ('c46d5a28-b0d5-4b03-b4ca-1b290f45918a', 'Alton Legros Sr.');
insert into customer (customer_id, name) values ('a9e6d48a-70ae-4318-82bd-72fb4121d5d1', 'Catheryn Wehner III');
insert into customer (customer_id, name) values ('32be6a20-940a-488a-9cf8-57f8271766b3', 'Kendrick Okuneva');
insert into customer (customer_id, name) values ('f58e8ade-0e04-44ca-be97-cd8e274f0cf0', 'Raymon Schaden Esq.');
insert into customer (customer_id, name) values ('eea1b623-4919-48d8-b373-4e046d107308', 'Dr. Alayna Conroy');
insert into customer (customer_id, name) values ('3d49669b-4362-4366-a7ad-48fecbed5a80', 'Hildred Greenholt');
insert into customer (customer_id, name) values ('2cd22002-139a-46ed-b780-fa5cfb84845a', 'Hailey Bradtke');
insert into customer (customer_id, name) values ('cd2a4209-f516-409b-bb23-2af862e7250b', 'Scot Nicolas Esq.');
insert into customer (customer_id, name) values ('53a44d25-71c1-48fe-a96c-5c070a4cf59d', 'Glenn Schaefer');
insert into customer (customer_id, name) values ('ac8fb678-2fcd-4895-9ffe-cde6cae2a623', 'Xochitl Kessler V');
insert into customer (customer_id, name) values ('c7565606-c1d3-46e0-adea-76a425197877', 'Tory Hessel');
insert into customer (customer_id, name) values ('63e78fdc-49e0-4f58-9b6a-06a10fddd63f', 'Keisha Crist');
insert into customer (customer_id, name) values ('5f0a194d-b7b4-49c2-9f28-539a9a387c50', 'Pres. Mckinley Hettinger');
insert into customer (customer_id, name) values ('f443e4f4-fdbe-45e6-bb98-98a164caa7e9', 'Msgr. Jolynn Will');
insert into customer (customer_id, name) values ('1d785310-1b4e-4ef7-9bb9-43edd3e58f77', 'Lavern Sawayn');
insert into customer (customer_id, name) values ('24368fdb-f108-45ee-8d90-345bc1826443', 'Abbey Breitenberg');
insert into customer (customer_id, name) values ('265d3485-75d6-47bd-8147-0c8877dc7e38', 'Keith Cronin');
insert into customer (customer_id, name) values ('7a0966b0-e93b-47d2-8f75-8fa7e608ccff', 'Derrick Nitzsche VM');
insert into customer (customer_id, name) values ('88dbe597-acfd-48df-8758-77deb4e4c950', 'Tyson Orn VM');
insert into customer (customer_id, name) values ('c2127d53-9eb7-4c3f-9a8f-b9781c53d0aa', 'Sang Ullrich');
insert into customer (customer_id, name) values ('d6c0d9ca-4771-4200-abff-e51e37955fdd', 'Karyl Zboncak');
insert into customer (customer_id, name) values ('3b56f5e0-0b66-4f96-95a0-f383e3921efa', 'Miguel White');
insert into customer (customer_id, name) values ('111eaed6-c65b-49c2-84be-717586b600b3', 'Carmelo Smitham');
insert into customer (customer_id, name) values ('99a8c534-a9be-47a1-b6a8-8089bf72060a', 'Pres. Alfreda Nienow');
insert into customer (customer_id, name) values ('f228e33e-7705-4b2f-9cfc-6c2092189047', 'Patti Emard');
insert into customer (customer_id, name) values ('a4d107fa-1d69-4b0a-b6c8-adfd8873c99f', 'Jenelle Kiehn');
insert into customer (customer_id, name) values ('e930d829-cf42-43ff-a514-68009ce67730', 'Roxie Corkery');
insert into customer (customer_id, name) values ('1e65dbfd-726c-41d6-8c35-94a75b052c12', 'Micheal Hickle III');
insert into customer (customer_id, name) values ('82dddbb2-13cd-4887-bee1-feb13efa99b3', 'Pedro Breitenberg JD');
insert into customer (customer_id, name) values ('8343a497-4cfa-4e4a-91b1-9a100ba3f10d', 'Erasmo Nader');
insert into customer (customer_id, name) values ('c3415bd2-be92-4547-ad33-508ba6a09904', 'Rico Mertz');
insert into customer (customer_id, name) values ('a07904ca-d958-42e0-8121-3e07af65d6e4', 'Mr. Carl Swaniawski');
insert into customer (customer_id, name) values ('67203ede-0c69-48ff-8e05-29af37c22ba6', 'Delora Cormier');
insert into customer (customer_id, name) values ('7a509095-356b-4e8f-b176-1265da74e2c4', 'Prof. Jonathan Larkin');
insert into customer (customer_id, name) values ('5f51a860-897c-41a0-806e-ea0c5e16be51', 'Man Gutkowski DVM');
insert into customer (customer_id, name) values ('7264aefb-af44-4597-a3b7-ec902beda96f', 'Lea Kiehn');
insert into customer (customer_id, name) values ('4a99aced-d50a-4b11-89b4-9aff65370d98', 'Lia Langworth');
insert into customer (customer_id, name) values ('37a77db8-8745-446b-93d4-95b5102f275c', 'Marget Tremblay DC');
insert into customer (customer_id, name) values ('a4200d75-bbc3-4d19-abc7-a1dcd824957d', 'Vernie Schneider');
insert into customer (customer_id, name) values ('562451e3-5ac9-4b9c-9ec7-3bc85c3f3ba4', 'Melodie Heidenreich');
insert into customer (customer_id, name) values ('5c9a4174-a88a-47c2-be86-d32bf072ea76', 'Gov. Moriah DuBuque');
insert into customer (customer_id, name) values ('71b93c9e-cc9b-429a-b4b0-4f704ae44278', 'Phuong Farrell');
insert into customer (customer_id, name) values ('5562b309-5b06-4029-b4f4-3d50733bfcd7', 'Cornelius Bernier');
insert into customer (customer_id, name) values ('c079b16c-a712-4dcc-9c4e-988ee5b760b3', 'Wallace Barton');
insert into customer (customer_id, name) values ('bb7e21dc-c18a-4800-80f7-65465ec5f222', 'Arlen Denesik');
insert into customer (customer_id, name) values ('0bfacc4f-3e21-445e-bd20-a2712bee0001', 'Brant Lowe');
insert into customer (customer_id, name) values ('4a64aac8-c251-4d27-912e-e2e056c86049', 'Jerald Douglas');
insert into customer (customer_id, name) values ('60f4ae84-9599-4669-a868-333c457bd15b', 'Bradly Shanahan');
insert into customer (customer_id, name) values ('38fb6db5-e4c4-470d-b7ff-dba016863afa', 'Gwenda Feeney');
insert into customer (customer_id, name) values ('bb745c01-1941-4214-83cd-4caea3d9a11b', 'Darwin Haley');
insert into customer (customer_id, name) values ('af8af753-94b9-4e15-9ef6-716e242e0b84', 'Kerry Barrows III');
insert into customer (customer_id, name) values ('13023161-0f6d-4ade-bb2e-39f9ca798d4c', 'Jeff Quigley');
insert into customer (customer_id, name) values ('396ee3df-9385-4088-9070-7f161bd37136', 'Lanie Trantow');
insert into customer (customer_id, name) values ('0320a820-de82-4041-aa98-e301afe0f0ad', 'Ramon Bernhard');
insert into customer (customer_id, name) values ('0684caca-5c2f-424c-822c-0d0afd90badc', 'Jake Larson');
insert into customer (customer_id, name) values ('1b83563c-9d0c-486b-8abf-2e4142d1bd24', 'Gov. Barrett Schimmel');
insert into customer (customer_id, name) values ('2151130d-8343-433e-9a05-47f028299fc5', 'Jarrod Ruecker DC');
insert into customer (customer_id, name) values ('c6cacf63-22f4-4e4a-b6da-c8c832a76a4a', 'Marty Gleichner');
insert into customer (customer_id, name) values ('5ea61056-4ef6-42c2-b894-51612649a0f2', 'Dominic Bernhard');
insert into customer (customer_id, name) values ('6492061e-fb67-4fed-99c1-ff9a576fbaf3', 'Amb. Arnold Brakus');
insert into customer (customer_id, name) values ('e26cf801-5bb2-46ed-a892-d9e35e920329', 'Wayne Grant');
insert into customer (customer_id, name) values ('e528ded6-88fa-43ae-a4a2-ebfce0ed3a2d', 'Alden Champlin');
insert into customer (customer_id, name) values ('c883dd16-737a-4b08-b7d4-790e86cd03b9', 'Beverley Ratke');
insert into customer (customer_id, name) values ('48c7c37c-2ead-403d-ad14-656d9bbd5584', 'Darrel Runolfsson');
insert into customer (customer_id, name) values ('35fd75c1-88fd-4e52-abb0-3b096b5910bd', 'Regan VonRueden');
insert into customer (customer_id, name) values ('6086bb45-b024-4b9e-b42d-c4904f68d499', 'Gov. King Kris');
insert into customer (customer_id, name) values ('a38503c0-3aff-41c1-8439-5d1fb388bcf5', 'Korey Heidenreich');
insert into customer (customer_id, name) values ('f9317e66-6452-47e5-8247-4d2ed8ab45ed', 'Rev. George Collier');
insert into customer (customer_id, name) values ('bb22af20-ab74-4d03-bdf3-5d3c151cd40b', 'Joel Muller');
insert into customer (customer_id, name) values ('5af90ac8-3bf8-4a34-af98-0d2823151157', 'Elina Walter');
insert into customer (customer_id, name) values ('e0f782c8-68a9-4c61-98c3-e8b4599a3560', 'Prof. Marylynn Padberg');
insert into customer (customer_id, name) values ('0b5e5dd8-2901-41e9-b5b1-5b7f3afb39cd', 'Blair Becker');
insert into customer (customer_id, name) values ('9e7e8e55-9025-413f-82e0-a8607e235a73', 'Rep. Altha Mante');
insert into customer (customer_id, name) values ('668b649d-af87-4c90-a2c1-ff343b88ac52', 'Lashaun Nolan DC');
insert into customer (customer_id, name) values ('709c9180-b0cf-475f-9648-e742393eb437', 'Kathe Lakin');
insert into customer (customer_id, name) values ('2d8e9451-f0fc-4e10-9d83-e7ea0201291b', 'Vince Kshlerin');
insert into customer (customer_id, name) values ('bbbb613e-a6af-402b-8664-238dcd0184e8', 'Prof. Lourie Hammes');
insert into customer (customer_id, name) values ('e6a09205-06a4-4843-a492-f67c634fe443', 'Chere Swaniawski');
insert into customer (customer_id, name) values ('816c181a-a00d-4974-85ad-041c13f4f15c', 'Chantay Robel');
insert into customer (customer_id, name) values ('61a48106-00f3-412e-9a1e-3e36d2a99946', 'Rosemarie Nienow');
insert into customer (customer_id, name) values ('123591ec-f532-4148-85eb-49c13b41367b', 'Amb. Marni Beer');
insert into customer (customer_id, name) values ('4cc8bb31-8955-4669-a4ee-7f64b39b1e8d', 'Christeen Willms');
insert into customer (customer_id, name) values ('6f96cd0e-d97b-4a9d-aef8-17410c7bafd4', 'Mr. Boyd Braun');
insert into customer (customer_id, name) values ('a0d7b5fd-61aa-427b-afa4-9637e21ccb2f', 'Andrea Mitchell');
insert into customer (customer_id, name) values ('45874237-5f6e-4e25-8994-34c5fc10c278', 'Augustine Barton');
insert into customer (customer_id, name) values ('e8be834a-981d-4234-8c60-8bfd2342dc31', 'Gisele Roob');
insert into customer (customer_id, name) values ('684e28fa-33f6-4e55-9eb4-428be95b762a', 'Sen. Alease Beatty');
insert into customer (customer_id, name) values ('e1ac8323-028c-4a28-ad06-527c70846bef', 'Chara Simonis Esq.');
insert into customer (customer_id, name) values ('6c108a32-0a1f-4ee6-8085-dd1f2e2983fa', 'The Hon. Iesha Jones');
insert into customer (customer_id, name) values ('42098f5e-df0c-4dfb-8e97-b092b7587d14', 'Erich Nienow');
insert into customer (customer_id, name) values ('1a6c0373-d00d-4a91-a5b0-ee776c5aef3c', 'Dian Hyatt DDS');

insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b35a5f3e-d9e7-47bf-a5f8-e8abdc9166c0', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '840592510691', 261820, 393435, '2025-12-18T20:50:46.626206Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('a8fe04d7-c6e9-4857-be39-37559d653f33', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '348672000854', 271685, -1, '2025-12-18T20:50:46.726771Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b54ac68a-3eca-435c-8902-200a1a1b09ff', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '688535809702', 613554, 585208, '2025-12-18T20:50:46.727091Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('1ab2769c-a290-4b2f-9e27-da26985f69d8', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '137781046066', 939634, -1, '2025-12-18T20:50:46.727379Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('87fcf9bd-c496-499a-8ddd-b5f35af99931', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '769953365933', 245395, 622379, '2025-12-18T20:50:46.727414Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('7cc6cfa4-c1e9-494a-986b-bbf607bc5550', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '357551665805', 912883, 587583, '2025-12-18T20:50:46.727678Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('2c754a65-ff58-47e6-85c1-78eb1aca2225', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '489618561066', 551044, -1, '2025-12-18T20:50:46.727938Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('759c268e-84f7-4975-a0d9-19b2cd8f0dde', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '204419199013', 608643, -1, '2025-12-18T20:50:46.727973Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('095d08c2-5199-4d31-a548-12b5955c61a1', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '697211035848', 737225, 672483, '2025-12-18T20:50:46.728232Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('15ba1443-4cf1-4367-93b6-60fbb95a5b5e', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '643708518735', 888009, 545153, '2025-12-18T20:50:46.728265Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('4e6f9bb8-78a5-4e09-9673-cce1f5489a5b', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '275081522245', 368326, -1, '2025-12-18T20:50:46.728519Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('11e6699e-09c0-4e83-aa27-821896ae0c18', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '585063310780', 177910, 347544, '2025-12-18T20:50:46.728539Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('60daa3ab-6065-4f2b-830b-86b36bb4afe8', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '542043389920', 753065, 742704, '2025-12-18T20:50:46.728793Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('464300eb-3855-4132-b377-6fe93a794fee', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '601597800225', 361387, 651097, '2025-12-18T20:50:46.729036Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('1a445f91-2ead-438a-9157-e2306187972a', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '271846629187', 936161, -1, '2025-12-18T20:50:46.729064Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('18036c84-0da6-4c1e-9b11-126a55b485c7', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '630227355098', 788401, 557136, '2025-12-18T20:50:46.729362Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('07f047c6-eec0-4f71-98d8-0b06fac54ea9', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '129362149332', 738485, -1, '2025-12-18T20:50:46.729608Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('4d871fb3-f0e1-4dc9-8415-2546e8723250', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '015094957205', 529576, 887502, '2025-12-18T20:50:46.729627Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('839cc007-0190-43a3-a229-b97fe0fdddd1', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '860874537812', 87046, -1, '2025-12-18T20:50:46.729862Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('a44a2fb9-565b-468e-a23d-5956fdd2e744', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '476805684738', 171662, 741864, '2025-12-18T20:50:46.730118Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('58207bba-605a-4b23-98aa-9ac09a28437f', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '263399671355', 30509, -1, '2025-12-18T20:50:46.730333Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('ea73977d-dcbd-4fd4-81c2-5ac84fa64cac', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '460778380182', 241153, 848736, '2025-12-18T20:50:46.730361Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('1db7abdf-2504-4e24-99b3-3dffe16ee8eb', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '841031551382', 937519, 188013, '2025-12-18T20:50:46.730575Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('4130354e-87e6-4255-9189-522263c3027f', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '917752502053', 965368, -1, '2025-12-18T20:50:46.730590Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('10949264-8492-44d9-ba5c-65c4b7838df4', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '785479388858', 612620, 605770, '2025-12-18T20:50:46.730806Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('51d7b18f-7c8c-4230-a198-36ad0e73f79d', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '965254489127', 951234, -1, '2025-12-18T20:50:46.730833Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('6603d019-cf11-4464-af83-d73a6114d7d9', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '412397137800', 43237, 987620, '2025-12-18T20:50:46.731043Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('558c8707-3d8a-4375-8e7e-998395fb46e5', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '457041605373', 232130, 635764, '2025-12-18T20:50:46.731345Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('d9dbc87f-1fec-4d78-bf2b-ec41887dfd44', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '857979944479', 597922, 272650, '2025-12-18T20:50:46.731377Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('06e59082-1be0-4c86-87a5-ccf1f9fea164', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '026143793697', 732207, 362844, '2025-12-18T20:50:46.731585Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('1cf3a191-a526-4d97-a3d3-434d14f60ce3', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '181064717371', 729364, 792199, '2025-12-18T20:50:46.731790Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('a8a3a809-1348-41fe-b334-afa6e29e0493', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '306842149464', 894290, 159169, '2025-12-18T20:50:46.731997Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('119ea857-b60b-491c-8e01-ab437e50f26e', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '769638450787', 624651, 330272, '2025-12-18T20:50:46.732194Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('99d5f7aa-2bc3-4aab-be55-528b049579c6', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '450562884434', 979533, 966303, '2025-12-18T20:50:46.732414Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('7e557ea8-c91d-46eb-8ca1-0358f43dcabe', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '516104343619', 661214, 736190, '2025-12-18T20:50:46.732615Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('6c6bed17-97db-4220-bcd7-9c74bb5c30a0', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '911627258608', 471648, 343195, '2025-12-18T20:50:46.732827Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('400beeb3-50ab-4b56-8775-3b2b07aedbc3', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '193375828613', 302895, -1, '2025-12-18T20:50:46.732853Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('e7338740-b7ee-469c-9c74-bee7dd5f18d2', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '025595125408', 654097, 409217, '2025-12-18T20:50:46.733052Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('45e57df9-772b-40ca-aafc-898162c69844', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '646411892312', 822042, 660662, '2025-12-18T20:50:46.733274Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('9eddc067-b8c5-418d-8707-e41b27dafb46', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '001924656453', 122452, 931734, '2025-12-18T20:50:46.733474Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('1dc801b8-53d9-46a5-85fd-164140883dd0', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '797372148683', 930296, -1, '2025-12-18T20:50:46.733655Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('8c6f7203-3c40-4766-9212-79d89260e8e9', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '632072790394', 734832, -1, '2025-12-18T20:50:46.733829Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('a730d385-0f71-42a3-8b8c-b4195bfa8f1f', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '641270883217', 818615, -1, '2025-12-18T20:50:46.734009Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('5e62c6a4-2bb4-4e1c-8488-e10c7b1c3f63', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '692227952458', 378643, -1, '2025-12-18T20:50:46.734033Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('25cf4233-39d3-47dc-bf10-51d2687e74de', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '848923721955', 128151, 163868, '2025-12-18T20:50:46.734225Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('7d7c80c6-da3d-482a-87d2-4323b489f08b', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '205206786137', 63504, 567758, '2025-12-18T20:50:46.734248Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('f52dd1ff-6bbf-4473-8f2f-718db4a10f87', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '590938538881', 908973, 303440, '2025-12-18T20:50:46.734439Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('6e279e72-004f-470e-8bb4-0999c3fc6164', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '716418746499', 681979, -1, '2025-12-18T20:50:46.734467Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b5dc2142-0bad-4987-be8e-d11f22d9ea51', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '694247551823', 985766, -1, '2025-12-18T20:50:46.734667Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('2130aa47-2aba-4a23-b3ca-a4360325ae32', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '062280617629', 651237, 817154, '2025-12-18T20:50:46.734827Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('310f8c32-4e53-4985-bd1b-6da805a969db', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '123985912027', 321258, 686442, '2025-12-18T20:50:46.734991Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('0e73205f-4f6a-4208-ad93-9e5206df13c7', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '647493338509', 907455, 184623, '2025-12-18T20:50:46.735151Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('28be9ff8-6e8b-486b-bf5d-0d5f62c0c810', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '685434981990', 772270, 642296, '2025-12-18T20:50:46.735353Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('54835b8a-a27f-4145-a0f6-df40badc1d0e', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '786012566512', 247684, -1, '2025-12-18T20:50:46.735373Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('3628d3b6-b07f-41cd-8c09-8c99afa414ba', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '904976956458', 997089, 914366, '2025-12-18T20:50:46.735518Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('2c9eb637-701c-47c0-86f9-52df6517d923', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '978347468909', 381552, 555029, '2025-12-18T20:50:46.735540Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('dbc42853-f1be-402c-845f-6452bea1fcf0', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '366529843192', 977188, 672559, '2025-12-18T20:50:46.735691Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('e313b334-d8e0-4f9d-a841-02495ccfd0d4', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '320255467940', 867667, 670359, '2025-12-18T20:50:46.735878Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b55783b5-489c-4878-a0aa-c81825d0ae80', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '863312683395', 240951, 944721, '2025-12-18T20:50:46.735906Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('9303996d-4078-4c79-80db-6a3eaa36104a', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '249436591765', 964816, -1, '2025-12-18T20:50:46.736069Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('f38f3d11-ca55-4e14-9b7a-3c41774011e1', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '607647053430', 826374, -1, '2025-12-18T20:50:46.736089Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('8a1eb4a2-9b78-44bf-bb1e-2556e0dab34c', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '841537254236', 621226, 690486, '2025-12-18T20:50:46.736379Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('c855958a-64c0-4592-b32a-d7a4c8b2b733', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '764943880633', 586006, -1, '2025-12-18T20:50:46.736401Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('48ad0f18-b76e-4483-9afc-cd7e59be5f3a', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '570410414683', 689485, 538302, '2025-12-18T20:50:46.736611Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('a90a0979-3d6a-4c77-b2ce-81ba1f5311a5', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '039076619844', 312293, -1, '2025-12-18T20:50:46.736755Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('5d4da318-295f-43fd-b303-bbd5fc786145', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '470069653441', 465006, 901889, '2025-12-18T20:50:46.736901Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('25b1246f-0e37-44e9-a053-8fa48c7f8e1e', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '522523131477', 420503, 819524, '2025-12-18T20:50:46.737045Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('180627e6-0c19-44ba-82b6-4ec97d3ad85d', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '548230089200', 362633, 397495, '2025-12-18T20:50:46.737237Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('7fba12e8-f336-4a99-a479-dbcb1fc058b8', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '855991382459', 753002, 453878, '2025-12-18T20:50:46.737397Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('78560cc8-0cca-433a-8291-89c80601bf63', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '656366918976', 138103, -1, '2025-12-18T20:50:46.737539Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('85b8f4a0-8132-49e1-8991-32d3b09d6b71', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '825485987713', 565174, 474069, '2025-12-18T20:50:46.737683Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('d1f46208-0958-4793-bf89-39afc8da5c85', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '900344215979', 472995, -1, '2025-12-18T20:50:46.737856Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('67a1a352-63c6-43f9-9d71-ce28a20ae21c', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '678614250623', 105855, -1, '2025-12-18T20:50:46.737877Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('5f9a0ffa-c748-4fbb-95a5-8eab94c05210', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '780674805383', 436823, 890054, '2025-12-18T20:50:46.738034Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('08610465-eb20-4cef-a770-ee3cd8e75508', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '197120581945', 374058, 908135, '2025-12-18T20:50:46.738155Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('14d26f53-dd5e-4cfa-aada-67c21bb5d939', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '722749040232', 320440, 743761, '2025-12-18T20:50:46.738178Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b5c5b6ab-0337-4187-afc3-a1ec6230064f', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '967326912852', 403619, 292283, '2025-12-18T20:50:46.738299Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('a9b5bf1c-cbe0-4d26-9be9-e219091870fb', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '683883070220', 696178, 918677, '2025-12-18T20:50:46.738403Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('2993ded9-071e-45bc-9b13-accdf5d3ebec', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '097825085523', 16489, -1, '2025-12-18T20:50:46.738422Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('78699c1e-3234-4c39-a2d4-5e6e545dc68e', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '165156091485', 607115, -1, '2025-12-18T20:50:46.738542Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('7d7d78f3-acce-4f6a-9cea-2a3afcde3e8f', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '284004349455', 18397, 907758, '2025-12-18T20:50:46.738553Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('83ca04ab-5096-4031-b64a-163ca57dfb1b', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '170415967084', 790666, -1, '2025-12-18T20:50:46.738667Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('96e28973-b877-402f-b3ae-36ec55c8844e', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '004896282606', 851728, 500111, '2025-12-18T20:50:46.738683Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('a43c454e-eaae-4ccf-ba48-24cf178c0031', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '369543293061', 11952, -1, '2025-12-18T20:50:46.738788Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b154fd49-3672-4066-8fe1-a9993e912518', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '560235725076', 119048, 264411, '2025-12-18T20:50:46.738894Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('0a48b595-c065-4fc3-81fc-25305be2e919', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '444413231251', 816367, -1, '2025-12-18T20:50:46.739002Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('cce1aaa6-a333-477e-8c63-815bef486b25', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '745064002818', 485845, 859149, '2025-12-18T20:50:46.739013Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('f5a4a30f-bf41-44c8-b3b3-283ffe260a60', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '210244786500', 803334, -1, '2025-12-18T20:50:46.739146Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('64afb508-3fdb-4f14-a721-040f46ad6d54', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '625936508606', 981190, -1, '2025-12-18T20:50:46.739159Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('853a8faf-f277-4783-a080-1a32165bdc6e', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '255168514598', 899415, 429742, '2025-12-18T20:50:46.739339Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('e121b8a7-f238-42b9-8457-181650d599f1', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '940660878428', 36625, -1, '2025-12-18T20:50:46.739348Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('cd0aac86-7ac4-4828-8feb-b4a36d4f517c', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '782026064957', 644429, 858787, '2025-12-18T20:50:46.739506Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('385ddbb9-9e74-4532-b04a-de4e304cf8a7', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '523158747181', 230637, 906430, '2025-12-18T20:50:46.739633Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('01961663-3143-4f5c-92e7-965eec12a0f9', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '361668045525', 470678, 205760, '2025-12-18T20:50:46.739743Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('2daeec3a-f864-4eba-bdf5-03ccf9feb992', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '033677131742', 463157, 919230, '2025-12-18T20:50:46.739758Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('78fd87d1-526d-4b18-8de8-c4c281977037', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '955834191412', 87562, 167755, '2025-12-18T20:50:46.739874Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('d7ea63f6-7280-453f-83c2-3f8ddc60ef79', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '047527900947', 565513, -1, '2025-12-18T20:50:46.739974Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('29e9ab61-e061-4d94-8cd3-c99446d51c55', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '698891855732', 726321, -1, '2025-12-18T20:50:46.740078Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('384df65c-8611-4041-99a2-9b5a19d87245', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '317084076817', 383592, 633908, '2025-12-18T20:50:46.740092Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('3535f85d-9583-4470-ac4c-9afdc1038270', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '804034073342', 368213, 973931, '2025-12-18T20:50:46.740195Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('cd4373fe-0091-4421-898d-1322bb7921e8', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '554527541526', 986427, 738178, '2025-12-18T20:50:46.740285Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('e731b69c-eeaf-4cae-8db6-e0cb7a703579', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '837854154774', 128243, 927070, '2025-12-18T20:50:46.740297Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('d59927c9-8f84-405f-b928-2ec21f8c6eb1', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '801651013919', 211297, -1, '2025-12-18T20:50:46.740390Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('65d47575-b37f-4501-841e-142b28a09398', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '046504650855', 262630, -1, '2025-12-18T20:50:46.740411Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('32fffa33-1cd7-46d6-8e2e-e98450886d01', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '737787755729', 99722, 949978, '2025-12-18T20:50:46.740514Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('e3dc7114-400f-4423-b05f-d6c737c28279', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '208075196249', 704779, -1, '2025-12-18T20:50:46.740610Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('68dd02c1-3e68-4d4d-8773-fe9569808537', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '645973919249', 967878, 900010, '2025-12-18T20:50:46.740623Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('e413f084-e90e-424a-b205-f3dd75142edc', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '828932115835', 58958, 723256, '2025-12-18T20:50:46.740724Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('8a5a20aa-c7aa-4e06-b3af-7285634af283', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '946700070608', 18755, 724151, '2025-12-18T20:50:46.740811Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('fadaa8d2-d11e-48f4-b113-7ae97265e717', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '050692159367', 121444, -1, '2025-12-18T20:50:46.740905Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('47cd9c20-4d9e-4b8a-813b-7cf5ccc918cd', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '485657532975', 243864, 643387, '2025-12-18T20:50:46.740918Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('54d734a2-73fa-4c9d-8cf3-7a39eaef3b2d', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '860291296970', 114769, -1, '2025-12-18T20:50:46.741027Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b391f9a6-4399-4069-9150-100fa9423b03', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '800021709919', 849810, -1, '2025-12-18T20:50:46.741130Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('98eab8e7-ae72-4457-9ea0-742b7ae8093d', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '342488397981', 143322, 248880, '2025-12-18T20:50:46.741233Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('0305e738-0366-4b59-9641-5036465d07cd', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '747459571418', 588444, 541704, '2025-12-18T20:50:46.741329Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('cf8d0727-06ae-41b7-a2bf-c14ea6affcfa', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '314261250253', 272158, 696296, '2025-12-18T20:50:46.741339Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('0a672532-6002-4b7d-bee3-274a62a9fb5a', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '047179100420', 566487, 768136, '2025-12-18T20:50:46.741440Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('12ff259b-fcec-4111-8a3e-92f1b34ef005', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '099052933461', 625302, -1, '2025-12-18T20:50:46.741533Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('394c6a7f-f4e5-40b8-bf5b-a953d624f184', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '234723738928', 422815, 614134, '2025-12-18T20:50:46.741634Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('22a5a25e-618e-456a-b80a-9cbef2c43460', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '052929949749', 84647, -1, '2025-12-18T20:50:46.741731Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('1277cc00-3031-42e9-bb55-37ca86bbfb7c', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '232077581769', 535338, 154707, '2025-12-18T20:50:46.741830Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b2063c94-d6ff-4b99-9b1a-191d9fc22602', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '013700886605', 619812, 955503, '2025-12-18T20:50:46.741915Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('0532f619-2dfb-4ed2-8656-ce67a56128aa', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '917884619045', 338226, 399240, '2025-12-18T20:50:46.741929Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('db21b201-6b2e-4e52-ad8d-d44123fc5c63', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '475237487636', 267666, 408918, '2025-12-18T20:50:46.742030Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('3b07c7ff-6fed-490a-a9e1-f5bafebc6f0b', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '125342867191', 834949, 762789, '2025-12-18T20:50:46.742041Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('c63bfa5b-d5bb-461c-b9eb-e8744301a90e', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '883963223240', 248001, 506688, '2025-12-18T20:50:46.742153Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('21140c1d-976c-4879-8bdc-a2a06014a372', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '800908994039', 397168, 612766, '2025-12-18T20:50:46.742236Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('4b2c12b4-cdf9-4dc2-b23a-09610cc147eb', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '393203349528', 987742, 541496, '2025-12-18T20:50:46.742330Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('4b3124e8-5fe8-4c26-8e72-8f6ba2766978', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '267291156847', 954817, -1, '2025-12-18T20:50:46.742432Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('b31626b2-19f3-473f-8056-240a12f26768', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '711420711105', 970647, 867913, '2025-12-18T20:50:46.742444Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('245f557e-4c75-4015-b6b5-3a52111e716f', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '504616613637', 361285, 847457, '2025-12-18T20:50:46.742531Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('3fe4fa6b-719c-428e-ab19-ac1e5a83f0f1', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '797489464969', 264882, -1, '2025-12-18T20:50:46.742612Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('87d34d56-3bfc-4b8e-b3cf-5f8fc1df2732', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '758204059121', 629229, 260572, '2025-12-18T20:50:46.742623Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('8536988f-ed41-448c-97ce-9555c75abfaa', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '054164644317', 438081, -1, '2025-12-18T20:50:46.742712Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('9bb7ec36-dda1-4ff9-a738-6f7f2e9f7630', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '359503338864', 434121, -1, '2025-12-18T20:50:46.742810Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('27a6c30d-3dd4-430c-9b72-6a22b6bc28be', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '465841016173', 842926, 299443, '2025-12-18T20:50:46.742823Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('5065fc4a-0d76-4ae7-84f0-b17d57bc7ce8', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '403048487366', 508214, 957365, '2025-12-18T20:50:46.742904Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('cb0c31f6-0fd6-46de-af02-5d25005aa5bb', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '918352928034', 606700, 901985, '2025-12-18T20:50:46.742986Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('04d0fe46-e270-4bf0-b721-ee363d6a6e2f', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '469309208891', 678618, 595599, '2025-12-18T20:50:46.742996Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('d3f7b752-a107-4df3-9795-14db35826c73', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '614894611657', 968094, 864957, '2025-12-18T20:50:46.743085Z');
insert into account (account_id, bank_id, routing_number, amount, withdrawal_limit, last_change) values ('c9fc8fa1-ca0e-4b1e-904c-80e5d80b2e39', '5e3b38dc-7c1c-410e-ac70-8bdebddd0736', '028171413533', 495907, -1, '2025-12-18T20:50:46.743097Z');