package CAMs_App.enums;

import CAMs_App.entity.Staff;
import CAMs_App.entity.User;

/**
 * The {@link Faculty} enum represents various faculties in NTU in the system.
 * The {@link User} is allocated to a single faculty
 * 
 *  @author Wan Ismail
 *  @version 1.0
 *  @since 2023-10-25
 */
public enum Faculty{
    /**
     * {@link Student} belongs to one of the following faculties
     */
    /**
     * Faculty for Nanyang Institute of Education.
     */
    NIE,
    /**
     * Faculty for Nanyang Business School.
     */
    NBS,
    /**
     * Faculty for School of Electrical and Electronic Engineering.
     */
    EEE,
    /**
     * Faculty for School of Humanities.
     */
    SOH,
    /**
     * Faculty for School of Mechanical and Aerospace Engineering.
     */
    MAE,
    /**
     * Faculty for School of Computer Science and Engineering.
     */
    SCSE,
    /**
     * Faculty for School of Physical and Mathematical Sciences.
     */
    SPMS,
    /**
     * Faculty for School of Social Sciences.
     */
    SSS,
    /**
     * Faculty for College of Continuing and Lifelong Education.
     */
    CCEB,
    /**
     * Faculty for Lee Kong Chian School of Medicine.
     */
    LKC,
    /**
     * Faculty for School of Biological Science.
     */
    SBS,
    /**
     * Faculty for School of Civil and Environmental Engineering.
     */
    CEE,
    /**
     * Faculty for School of Materials Science and Engineering.
     */
    MSE,
    /**
     * Faculty for Wee Kim Wee School of Communication and Information.
     */
    WKWSOC,
    /**
     * Faculty for School of Art, Design and Media.
     */
    ADM,
    /**
     * Faculty for Asian School of the Environment.
     */
    ASE,
    /**
     * Faculty for School of Chemistry,Chemical Engineering and Biomedical.
     */
    SCBE,
    /**
     * University-wide camp, anyone can attend
     * {@link Staff} uses to NTU faculty
     */
    NTU; 
};
