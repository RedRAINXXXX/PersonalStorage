package com.zzy.investeval.service;

import com.zzy.investeval.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.hibernate.internal.util.StringHelper.isEmpty;

/**
 * 评价报告业务逻辑接口实现类
 *
 * @author 赵正阳
 */
@Service
public class ReportServiceImpl implements ReportService {
	private final FileRepository reportFileRepository;

	@Autowired
	public ReportServiceImpl(@Qualifier("reportFileRepository") FileRepository reportFileRepository) {
		this.reportFileRepository = reportFileRepository;
	}

	@Override
	public List<String> findAllKeys() {
		return reportFileRepository.findAllKeys();
	}

	@Override
	public String findReportFilenameById(Integer projectId, Integer investorId) {
		return reportFileRepository.findFilename(String.format("%d-%d", projectId, investorId));
	}

	@Override
	public byte[] readReportContentById(Integer projectId, Integer investorId) {
		return reportFileRepository.readContent(String.format("%d-%d", projectId, investorId));
	}

	@Override
	public void saveReport(Integer projectId, Integer investorId, MultipartFile report) throws IOException {
		String key = String.format("%d-%d", projectId, investorId);
		if (report != null && !isEmpty(report.getOriginalFilename())) {
			reportFileRepository.delete(key);
			reportFileRepository.save(key, report.getOriginalFilename(), report.getInputStream());
		}
	}

	@Override
	public void deleteReport(Integer projectId, Integer investorId) {
		reportFileRepository.delete(String.format("%d-%d", projectId, investorId));
	}

	@Override
	public void deleteReportByProject(Integer projectId) {
		reportFileRepository.findAllKeys().stream()
				.filter(key -> key.contains("-") && key.split("-")[0].equals(projectId.toString()))
				.forEach(reportFileRepository::delete);
	}

	@Override
	public void deleteReportByInvestor(Integer investorId) {
		reportFileRepository.findAllKeys().stream()
				.filter(key -> key.contains("-") && key.split("-")[1].equals(investorId.toString()))
				.forEach(reportFileRepository::delete);
	}

}
