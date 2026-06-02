CREATE DATABASE BDAcademica;
GO
USE BDAcademica;
GO

CREATE TABLE Archivos_Entregados (
    id_archivo INT PRIMARY KEY IDENTITY(1,1),
    nombre_real VARCHAR(150) NOT NULL,
    tamano_mb DECIMAL(5,2) NOT NULL,
    ruta_local VARCHAR(255) NOT NULL,
    fecha_subida DATETIME DEFAULT GETDATE()
);

CREATE TABLE Tareas_Pendientes (
    id_tarea INT PRIMARY KEY IDENTITY(1,1),
    titulo VARCHAR(200) NOT NULL,
    prioridad VARCHAR(10) CHECK (prioridad IN ('ALTA', 'MEDIA', 'BAJA')),
    fecha_creacion DATETIME DEFAULT GETDATE()
);
