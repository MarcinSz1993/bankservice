--liquibase formatted sql

--changeset MarcinSz1993:6

INSERT INTO client (name, surname, pesel, password)
VALUES
('John', 'Doe','85313276062','$2a$12$8SIXxfb47gwP2Xa.RqCR.u.RXyx2rUf0UNjKDELHFXcxry3NEmA9S'),
('Jane', 'Smith','90836073478','$2a$12$fVQSgP3aUsvrQC2wJ1PrWeHEdAzUGXicqaWX3c/8L1esEHbEOAx4a'),
('Michael', 'Johnson','00877292541','$2a$12$mQBKu9NcdeqYKaLgdNLBo.A0q5RAIps2DHVh2t9G3xzshKzUTEjNu'),
('Emily', 'Davis','77322141407','$2a$12$wU.flRzQVqvgC/9BRLfOnOgYg5EYxZSO/6tylKEFjkFneEquUyv0K'),
('David', 'Wilson','68365413153','$2a$12$DdogggMYFXpdLicgOU615OpdwuIFOS8iU4J3wqvy7K1UviAkzIEdi'),
('Sarah', 'Brown','63439781328','$2a$12$LLjQpduwsedtHK/zFzk2CuJSvjgV3NZGoMOb7OZo9VsDoT6R1dl42'),
('Chris', 'Lee','67960273927','$2a$12$Cp3XMr3jdX.cZNdmS5ybx.OHAJXQCV5VRjUXQ4bV/mFbmHCeNv.BW'),
('Laura', 'Martin','91222418783','$2a$12$TfsXyDM4MhYowXtqSx4kk.QTFbgtXXaVHPa1lJAKGcuqHqyP7ES/W'),
('Robert', 'Clark','92455728824','$2a$12$RBf/cvjFJfv7n1VDS6yLUOer3cntFAj.t96BxaPhvjglig54T7FB2')