CREATE DATABASE gestor_tarefas;

CREATE SCHEMA esig_tarefas;

CREATE TABLE esig_tarefas.prioridade(
	id int not null primary key GENERATED ALWAYS AS IDENTITY,
	ds_prioridade varchar(40) not null
);

CREATE TABLE esig_tarefas.responsavel(
	id int not null primary key GENERATED ALWAYS AS IDENTITY,
	nome varchar(80) not null
);

CREATE TABLE esig_tarefas.tarefa(
	id int not null primary key GENERATED ALWAYS AS IDENTITY,
	id_responsavel int not null,
	id_prioridade int not null,
	titulo varchar(200) not null,
	descricao text not null,
	deadline date not null,
	st_concluida boolean not null default false,
	constraint fk_tar_res foreign key (id_responsavel) references esig_tarefas.responsavel(id),
	constraint fk_tar_pri foreign key (id_prioridade) references esig_tarefas.prioridade(id)
);